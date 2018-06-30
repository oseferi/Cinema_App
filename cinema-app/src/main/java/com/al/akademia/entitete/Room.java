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
@Table(name = "room")
public class Room  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 11)
	private int id;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "description", length = 500)
	private String description;

	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "cinema_id", nullable = false)
	private Cinema cinema;

	public Room() {
	}

	public Room(String name, String decription, Cinema cinema) {
		this.name = name;
		this.description = decription;
		this.cinema = cinema;
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

	public void setDescription(String decription) {
		this.description = decription;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", name=" + name + ", decription=" + description + ", cinema_id=" + cinema + "]";
	}

}
