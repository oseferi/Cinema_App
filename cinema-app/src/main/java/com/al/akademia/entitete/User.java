package com.al.akademia.entitete;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 11)
	private int id;
	@Column(name = "username", unique = true, nullable = false, length = 100)
	private String username;
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	@Column(name = "firstname", nullable = false, length = 100)
	private String firstName;
	@Column(name = "surname", nullable = false, length = 100)
	private String surName;
	@Column(name = "email", nullable = false, length = 100)
	private String email;
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	public User() {
		
	}
	public User(String username, String password, String firstName, String surName, String email, Role role) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.surName = surName;
		this.email = email;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", surName=" + surName + ", email=" + email + ", roleId=" + role + "]";
	}

}
