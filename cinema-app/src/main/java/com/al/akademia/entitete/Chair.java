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
@Table(name = "chair")
public class Chair  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable =false,length=11)
	private int id;
	
	@Column(name = "number",nullable=false,length=11)
	private int number;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "monitor_id",nullable=false)
	private Monitor monitor;
	
	@Column(name = "description",length=500)
	private String description;

	public Chair() {
		
	}
	public Chair(int number, Monitor monitor, String description) {
		this.number = number;
		this.monitor = monitor;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	
	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Chair [id=" + id + ", number=" + number + ", monitor=" + monitor + ", description=" + description + "]";
	}
	
	
}
