package com.julianomengue.classes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fotos")
public class Foto {

	private String id;

	private String title;

	private List<String> owners = new ArrayList<String>();

	private Long size;

	public Foto() {
	}

	public Foto(String id, String title, List<String> owners, Long size) {
		super();
		this.id = id;
		this.title = title;
		this.owners = owners;
		this.size = size;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public List<String> getOwners() {
		return owners;
	}

	public void setOwners(List<String> owners) {
		this.owners = owners;
	}

	public void addOwners(String owner) {
		this.owners.add(owner);
	}

	public void removeOwners(String owner) {
		for (int i = 0; i < this.owners.size(); i++) {
			if (this.owners.get(i).contentEquals(owner)) {
				this.owners.remove(i);
			}
		}
	}

}
