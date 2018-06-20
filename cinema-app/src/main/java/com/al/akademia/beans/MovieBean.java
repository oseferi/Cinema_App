
package com.al.akademia.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.al.akademia.dao.CategoryDao;
import com.al.akademia.dao.CountryDao;
import com.al.akademia.dao.MovieDao;
import com.al.akademia.entitete.Category;
import com.al.akademia.entitete.Country;
import com.al.akademia.entitete.Movie;

@ManagedBean(name = "movieBean")
@SessionScoped
public class MovieBean {
	private Movie movie;
	private Movie movieToBeUpdated;
	private List<Movie> movies;
	private List<Category> categories;
	private List<Country> countries;
	private int country;
	private int countryToBeUpdated;
	private int category;
	private int categoryToBeUpdated;
	private String successMessage;

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public Movie getMovieToBeUpdated() {
		return movieToBeUpdated;
	}

	public void setMovieToBeUpdated(Movie movieToBeUpdated) {
		this.movieToBeUpdated = movieToBeUpdated;
	}

	public int getCountryToBeUpdated() {
		return countryToBeUpdated;
	}

	public void setCountryToBeUpdated(int countryToBeUpdated) {
		this.countryToBeUpdated = countryToBeUpdated;
	}

	public int getCategoryToBeUpdated() {
		return categoryToBeUpdated;
	}

	public void setCategoryToBeUpdated(int categoryToBeUpdated) {
		this.categoryToBeUpdated = categoryToBeUpdated;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public MovieBean() {

		movie = new Movie();
		movies = MovieDao.getMoviesFromDB();
		categories = CategoryDao.getAllCategories();
		countries = CountryDao.getAllCountries();
		movieToBeUpdated = new Movie();

	}

	/************************************
	 * G E T T E R S / S E T T E R S
	 ***********************************/

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	/*****************************************
	 * M A N I P U L A T I O N L O G I C
	 ****************************************/

	public String addMovie() {
		Movie movie1 = new Movie();
		movie1.setCategory(CategoryDao.getCategoryById(category));
		movie1.setCountry(CountryDao.getCountryById(country));
		movie1.setTitle(movie.getTitle());
		movie1.setImdb(movie.getImdb());
		movie1.setDescription(movie.getDescription());
		movie1.setDirector(movie.getDirector());
		movie1.setDuration(movie.getDuration());
		movie1.setRating(movie.getRating());
		movie1.setYear(movie.getYear());

		if (MovieDao.addMovie(movie1)) {
			System.out.println("movie added");

			 updateMovies();
		} else {

			//updateMovies();
		System.out.println("movie was not added");
		}
		return null;
	}

	public String deleteMovie(String id) {

		if (MovieDao.deleteMovie(Integer.valueOf(id))) {
			System.out.println("movie removed");
			updateMovies();
		} else {
			System.out.println("movie was not removed");
		}
		return null;
	}

	public void updateMovies() {
		movies = MovieDao.getMoviesFromDB();
	}

	public String editAction(int movieId) {
		movieToBeUpdated = MovieDao.getMovieById(movieId);
		return "edit_movie";
	}

	public String updateAction(int id, Movie movie) {

		Category newCategory = CategoryDao.getCategoryById(categoryToBeUpdated);
		Country newCountry = CountryDao.getCountryById(countryToBeUpdated);
		movie.setCategory(newCategory);
		movie.setCountry(newCountry);
		
		
		if(MovieDao.updateMovie(id, movie)) {
			successMessage="Movie with Id "+ String.valueOf(id) + " updated succesfully";
			updateMovies();
			return "response_movie";
		}
		else {
			successMessage="Movie with Id "+ String.valueOf(id) + "was not updated succesfully";
			return "response_movie";
		}

    }
}
