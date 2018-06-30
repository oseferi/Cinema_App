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
@Table(name = "monitor")
public class Monitor implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 11)
	private int id;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;
	
	@Column(name = "description", length = 500)
	private String description;

	public Monitor() {
		
	}
	public Monitor(String name, Room room, String description) {
		this.name = name;
		this.room = room;
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Monitor [id=" + id + ", name=" + name + ", roomId=" + room + ", description=" + description + "]";
	}

}
