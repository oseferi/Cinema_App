package com.al.akademia.entitete;

import javax.faces.bean.ManagedBean;
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
import javax.persistence.Transient;

@Entity
@Table(name = "movie")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 11)
	private int id;
	
	@Column(name = "title", nullable = false, length = 100)
	private String title;
	
	@Column(name = "description", length = 3000)
	private String description;
	
	@Column(name = "imdb", nullable = false, unique = true, length = 45)
	private String imdb;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	private  Category category;
	
	@Column(name = "duration", nullable = false)
	private int duration;
	
	@Column(name = "director", nullable = false, length = 200)
	private String director;
	
	@Column(name = "year", nullable = false, length = 4)
	private int year;
	
	@Column(name = "rating", length = 1)
	private int rating;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id", nullable = false)
	private  Country country;

	@Transient
	private boolean  editable;
	
	public Movie() {
		this.title = null;
		this.description = null;
		this.imdb = null;
		this.category = null;
		this.duration = 0;
		this.director = null;
		this.year = 0;
		this.rating = 0;
		this.country = null;

	}

	public Movie(String title, String description, String imdb, Category category, int duration, String director,
			int year, int rating, Country country) {
		this.title = title;
		this.description = description;
		this.imdb = imdb;
		this.category = category;
		this.duration = duration;
		this.director = director;
		this.year = year;
		this.rating = rating;
		this.country = country;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImdb() {
		return imdb;
	}

	public void setImdb(String imdb) {
		this.imdb = imdb;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", description=" + description + ", imdb=" + imdb
				+ ", categoryId=" + category + ", duration=" + duration + ", director=" + director + ", year=" + year
				+ ", rating=" + rating + ", countryId=" + country + "]";
	}
	
	
}
