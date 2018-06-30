
package com.al.akademia.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.al.akademia.dao.CategoryDao;
import com.al.akademia.dao.CountryDao;
import com.al.akademia.dao.MovieDao;
import com.al.akademia.entitete.Category;
import com.al.akademia.entitete.Country;
import com.al.akademia.entitete.Movie;

@ManagedBean(name = "movieBean")
@ViewScoped
public class MovieBean implements Serializable{
	private Movie movie;
	private Movie movieToBeUpdated;
	private List<Movie> movies;
	private List<Category> categories;
	private List<Country> countries;
	private int country;
	private int countryToBeUpdated;
	private int category;
	private int categoryToBeUpdated;

	private String script;

	
	
	
	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
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
		
		if(FacesContext.getCurrentInstance().getExternalContext().getFlash().get("id")!=null) {
			movieToBeUpdated = MovieDao.getMovieById(Integer.valueOf(String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getFlash().get("id"))));
		}else {
		movieToBeUpdated = new Movie();
		}
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

	
	/**************************************************
	 *D A T A    M A N I P U L A T I O N    L O G I C
	 *************************************************/

	public String addMovie() {
		Movie movieToAdd = new Movie();
		movieToAdd.setCategory(CategoryDao.getCategoryById(category));
		movieToAdd.setCountry(CountryDao.getCountryById(country));
		movieToAdd.setTitle(movie.getTitle());
		movieToAdd.setImdb(movie.getImdb());
		movieToAdd.setDescription(movie.getDescription());
		movieToAdd.setDirector(movie.getDirector());
		movieToAdd.setDuration(movie.getDuration());
		movieToAdd.setRating(movie.getRating());
		movieToAdd.setYear(movie.getYear());

		if (MovieDao.addMovie(movieToAdd)) {
			System.out.println("movie added");
			setScript("alert('Movie with title "+movieToAdd.getTitle()+" was added succesfully.'); window.location.replace(window.location.href.replace('edit_movie','movies'));");
			refreshMovieFields();
			updateMovies();
		} else {

		setScript("alert('Movie with title "+movieToAdd.getTitle()+" failed to be added.If the problem persists please contact the administrator');");
		System.out.println("movie was not added");
		}
		return null;
	}

	public String deleteMovie(String id) {

		if (MovieDao.deleteMovie(Integer.valueOf(id))) {
			System.out.println("movie with id"+id+" removed");
			setScript("alert('Movie was deleted succesfully.');");
			updateMovies();
		} else {
			System.out.println("movie was not removed");
			setScript("alert('Movie cannot be deleted .');");
		}
		return null;
	}

	public void updateMovies() {
		movies = MovieDao.getMoviesFromDB();
	}

	public String editAction(int movieId) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("id", movieId );
		return "edit_movie?faces-redirect=true";
	}

	public String updateAction(int id, Movie movie) {
		System.out.println(categoryToBeUpdated);
		System.out.println(countryToBeUpdated);
		Category newCategory = CategoryDao.getCategoryById(categoryToBeUpdated);
		Country newCountry = CountryDao.getCountryById(countryToBeUpdated);
		movie.setCategory(newCategory);
		movie.setCountry(newCountry);
		
		
		if(MovieDao.updateMovie(id, movie)) {
			setScript("alert('Movie was updated succesfully.'); window.location.replace(window.location.href.replace('edit_movie','movies'));");
			updateMovies();
			return null;
		}
		else {
			setScript("alert('Movie could not be updated.');");
			return null;
		}
		
    }
	
	public void refreshMovieFields() {
		movie = new Movie();
	}
}
