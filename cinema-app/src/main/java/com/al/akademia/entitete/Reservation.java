package com.al.akademia.entitete;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reservation")
public class Reservation  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 11)
	private int id;
	@Column(name = "person", nullable = false, length = 200)
	private String person;
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "chair_id", nullable = false,unique=true)
	private Chair chair;

	@OneToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "show_id", nullable = false)
	private Show show;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	public Reservation() {

	}

	public Reservation(String person, User user, Chair chair, Show show, Date created) {
		this.person = person;
		this.user = user;
		this.chair = chair;
		this.show = show;
		this.created = created;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Chair getChair() {
		return chair;
	}

	public void setChair(Chair chair) {
		this.chair = chair;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", person=" + person + ", userId=" + user + ", chairId=" + chair + ", showId="
				+ show + ", created=" + created + "]";
	}

}
