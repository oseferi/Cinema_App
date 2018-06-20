package com.al.akademia.beans;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.al.akademia.dao.MonitorDao;
import com.al.akademia.dao.MovieDao;
import com.al.akademia.dao.ShowDao;
import com.al.akademia.entitete.Monitor;
import com.al.akademia.entitete.Movie;
import com.al.akademia.entitete.Show;

@ManagedBean(name = "showBean")
@SessionScoped
public class ShowBean {
	private Show show1;
	private List<Monitor> monitors;
	private List<Movie> movies;
	private int monitorId;
	private int movieId;
	private List<Show> shows;
	private int monitorUpdated;
	private int movieUpdated;
	private Show showToBeUpdated;
	private String successMessage;
	
	
	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public Show getShowToBeUpdated() {
		return showToBeUpdated;
	}

	public void setShowToBeUpdated(Show showToBeUpdated) {
		this.showToBeUpdated = showToBeUpdated;
	}

	public int getMonitorUpdated() {
		return monitorUpdated;
	}

	public void setMonitorUpdated(int monitorUpdated) {
		this.monitorUpdated = monitorUpdated;
	}

	public int getMovieUpdated() {
		return movieUpdated;
	}

	public void setMovieUpdated(int movieUpdated) {
		this.movieUpdated = movieUpdated;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	public Show getShow1() {
		return show1;
	}

	public void setShow1(Show show1) {
		this.show1 = show1;
	}

	public List<Monitor> getMonitors() {
		return monitors;
	}

	public void setMonitors(List<Monitor> monitors) {
		this.monitors = monitors;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public int getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(int monitorId) {
		this.monitorId = monitorId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}



	public ShowBean() throws IOException {
		
		show1 =new Show();
		movies = MovieDao.getMoviesFromDB();
		monitors = MonitorDao.getAllMonitors();
		showToBeUpdated = new Show();
		// updateShows();
		
	}

	public String addShow() throws ParseException {
		
		Show show = new Show();
		show.setMonitor(MonitorDao.getMonitorById(monitorId));
		show.setMovie(MovieDao.getMovieById(movieId));
		show.setStart(show1.getStart());
		if (ShowDao.addShow(show)) {
			System.out.println("show added");
			updateShows();
		} else {
			System.out.println("show was not added");
		}
		return null;
	}

	public String deleteShow(String id) {

		if (ShowDao.deleteShow(Integer.valueOf(id))) {
			System.out.println("show removed");
			updateShows();
		} else {
			System.out.println("show was not removed");
		}
		return null;
	}
	public String choose(String movieId) {
		shows= ShowDao.getShowFromDb1(Integer.valueOf(movieId));
		return "client_shows?faces-redirect=true";
		
	}
	public String chooseShow(String showId) {
		System.out.println(Integer.valueOf(showId));
		show1= ShowDao.getShowById(Integer.valueOf(showId));
		System.out.println(show1);
		return "client_reservation?faces-redirect=true";
		
		
	}

	public void updateShows() {
		shows = ShowDao.getShowsFromDB();
	}
	public String updateAction(int id, Show show) {
		Movie newMovie = MovieDao.getMovieById(movieUpdated);
		Monitor newMonitor = MonitorDao.getMonitorById(monitorUpdated);
		
		show.setMonitor(newMonitor);
		show.setMovie(newMovie);
		if (ShowDao.updateShow(id, show)) {
			updateShows();
			successMessage = "Show with Id " + String.valueOf(id) + " updated succesfully";
			System.out.println("po");
			return "response_show";
		} else {
			System.out.println("jo");
			successMessage = "Show with Id " + String.valueOf(id) + "was not updated succesfully";
			return "response_show";
		}

	}
	public String editAction(int showId) {
		showToBeUpdated = ShowDao.getShowById(showId);
		return "editShow";
	}
	
	
}
