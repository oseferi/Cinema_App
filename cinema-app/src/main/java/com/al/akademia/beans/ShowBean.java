package com.al.akademia.beans;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.al.akademia.dao.MonitorDao;
import com.al.akademia.dao.MovieDao;
import com.al.akademia.dao.ShowDao;
import com.al.akademia.entitete.Monitor;
import com.al.akademia.entitete.Movie;
import com.al.akademia.entitete.Show;

@ManagedBean(name = "showBean")
@ViewScoped
public class ShowBean{
	private Show show;
	private List<Monitor> monitors;
	private List<Movie> movies;
	private int monitorId;
	private int movieId;
	private List<Show> shows;
	private int monitorUpdated;
	private int movieUpdated;
	private Show showToBeUpdated;
	private String script;
	private String stringDate;
	private int stringHour;
	private int stringMinute;
	private String dateNow;
	
	
	public String getDateNow() {
		return dateNow;
	}

	public void setDateNow(String dateNow) {
		this.dateNow = dateNow;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}
	
	
	public String getStringDate() {
		return stringDate;
	}

	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}

	public int getStringHour() {
		return stringHour;
	}

	public void setStringHour(int stringHour) {
		this.stringHour = stringHour;
	}

	public int getStringMinute() {
		return stringMinute;
	}

	public void setStringMinute(int stringMinute) {
		this.stringMinute = stringMinute;
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

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show= show;
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
		
		show =new Show();
		movies = MovieDao.getMoviesFromDB();
		monitors = MonitorDao.getAllMonitors();
		showToBeUpdated = new Show();
		updateShows();
		dateNow= new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		if(FacesContext.getCurrentInstance().getExternalContext().getFlash().get("id")!=null) {
			showToBeUpdated = ShowDao.getShowById(Integer.valueOf(String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getFlash().get("id"))));
		}else {
		showToBeUpdated = new Show();
		}
		
	}

	public String addShow() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = null;
		try {
		   date = formatter.parse(stringDate+" "+String.valueOf(stringHour)+":"+String.valueOf(stringMinute));
		} catch (ParseException e) {
		  e.printStackTrace();
		}
		
		Show show = new Show();
		show.setMonitor(MonitorDao.getMonitorById(monitorId));
		show.setMovie(MovieDao.getMovieById(movieId));
		show.setStart(date);
		if (ShowDao.addShow(show)) {
			System.out.println("show added");
			setScript("alert('Show for the movie with title "+show.getMovie().getTitle()+" was added succesfully.');");
			updateShows();
		} else {
			System.out.println("show was not added");
			setScript("alert('Show for the movie with title "+show.getMovie().getTitle()+" was NOT added .</br> If the problem persists please contact the administrator');");

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
		System.out.println(showId);
		System.out.println(Integer.valueOf(showId));
		show= ShowDao.getShowById(Integer.valueOf(showId));
		//System.out.println(show);
		return "reservation1?faces-redirect=true";		
	}

	public void updateShows() {
		shows = ShowDao.getShowsFromDB();
	}
	public String updateAction(int id,Show show) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = null;
		try {
		   date = formatter.parse(stringDate+" "+String.valueOf(stringHour)+":"+String.valueOf(stringMinute));
		} catch (ParseException e) {
			date = new Date();
		  e.printStackTrace();
		}
		Movie newMovie = MovieDao.getMovieById(movieUpdated);
		Monitor newMonitor = MonitorDao.getMonitorById(monitorUpdated);
		show.setStart(date);
		show.setMonitor(newMonitor);
		show.setMovie(newMovie);
		if (ShowDao.updateShow(id,show)) {
			System.out.println("show added");
			setScript("alert('Show for the show with movie title "+show.getMovie().getTitle()+" was edited succesfully.');window.location.replace(window.location.href.replace('edit_Show','shows'))");
			updateShows();
			return null;
			
		} else {
			System.out.println("show was not edited");
			setScript("alert('Show for the show with movie title "+show.getMovie().getTitle()+" was NOT edited . If the problem persists please contact the administrator');");
			return null;
		}
		
	}
	public String editAction(int showId) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("id", showId );
		return "edit_Show?faces-redirect=true";
	}
}
