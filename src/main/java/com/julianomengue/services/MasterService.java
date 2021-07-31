package com.julianomengue.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.julianomengue.classes.Foto;
import com.julianomengue.classes.User;
import com.julianomengue.repositories.FotoRepository;
import com.julianomengue.repositories.UserRepository;

@Service
public class MasterService {

	@Autowired
	private FotoRepository fotoRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Foto> findAll() {
		return this.fotoRepo.findAll();
	}

	public List<Foto> getFotos() {
		Query query = new Query();
		query.fields().include("_id", "title", "owners", "size");
		List<Foto> list = mongoTemplate.find(query, Foto.class);
		return list;
	}

	public void deleteFotosWithoutOwners() {
		List<Foto> fotos = getFotos();
		for (int i = 0; i < fotos.size(); i++) {
			if (fotos.get(i).getId() != "610514c386d1db45a6de1c17"
					|| !fotos.get(i).getTitle().contentEquals("PROFILE_FOTO")) {
				if (fotos.get(i).getOwners().size() == 0) {
					delete(fotos.get(i));
				}
			}
		}

	}

	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

	public Foto findFotoById(String id) {
		return this.fotoRepo.findById(id).get();
	}

	public User findUserById(String id) {
		return this.userRepo.findById(id).get();
	}

	public void delete(Foto foto) {
		this.fotoRepo.delete(foto);
	}

	public void delete(User user) {
		this.userRepo.delete(user);
	}

}
