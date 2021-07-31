package com.julianomengue.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.julianomengue.classes.Foto;
import com.julianomengue.classes.User;
import com.julianomengue.services.MasterService;

@Controller
@RequestMapping("/")
public class MasterController {

	@Autowired
	private MasterService masterService;

	@GetMapping("/fotos")
	public String showFotos(Model model) {
		this.masterService.deleteFotosWithoutOwners();
		List<Foto> fotos = this.masterService.getFotos();
		model.addAttribute("fotos", fotos);
		return "html/fotos";
	}

	@GetMapping("/delete-foto")
	public String deleteFoto(@RequestParam String id, Model model) {
		Foto foto = this.masterService.findFotoById(id);
		this.masterService.delete(foto);
		model.addAttribute("fotos", this.masterService.findAll());
		return "html/fotos";
	}

	@GetMapping("/users")
	public String showUsers(Model model) {
		List<User> users = this.masterService.getUsers();
		model.addAttribute("users", users);
		return "html/users";
	}

	@GetMapping("/delete-user")
	public String deleteUser(@RequestParam String id, Model model) {
		User user = this.masterService.findUserById(id);
		this.masterService.delete(user);
		List<User> users = this.masterService.getUsers();
		model.addAttribute("users", users);
		return "html/users";
	}

}
