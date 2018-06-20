package com.al.akademia.entitete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="role")
public class Role {

	@Id
	@Column (name="id", nullable = false,length=11)
	private int id;
	@Column (name="name",nullable = false,length=100)
	private String name;
	@Column (name="description", length=500)
	private String description;
	
	public Role() {
		
	}
	public Role(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	
}
