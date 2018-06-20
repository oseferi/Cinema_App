package com.al.akademia.beans;

import java.text.ParseException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import com.al.akademia.dao.ChairDao;
import com.al.akademia.dao.ReservationDao;
import com.al.akademia.dao.ShowDao;
import com.al.akademia.dao.UserDao;
import com.al.akademia.entitete.Chair;
import com.al.akademia.entitete.Reservation;
import com.al.akademia.entitete.Show;
import com.al.akademia.entitete.User;

@ManagedBean(name = "reservationBean")
@ViewScoped
public class ReservationBean {
	private Reservation reservation;
	private int user;
	private int chair;
	private int show;
	private List<User> users;
	private List<Chair> chairs;
	private List<Show> shows;
	private List<Reservation> reservations;
	private String successMessage;
	
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	@ManagedProperty(value="#{showBean}")
	private ShowBean showBean;
	
	
	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getChair() {
		return chair;
	}

	public void setChair(int chair) {
		this.chair = chair;
	}

	public int getShow() {
		return show;
	}

	public void setShow(int show) {
		this.show = show;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Chair> getChairs() {
		return chairs;
	}

	public void setChairs(List<Chair> chairs) {
		this.chairs = chairs;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public ShowBean getShowBean() {
		return showBean;
	}

	public void setShowBean(ShowBean showBean) {
		this.showBean = showBean;
	}

	public ReservationBean() {
		reservations = ReservationDao.getReservationFromDb();
		reservation = new Reservation();
		shows = ShowDao.getShowsFromDB();
		users = UserDao.getUsersFromDB();
		chairs = ChairDao.getChairsFromDB();
	}

	/*public String addReservation() throws ParseException {

		Reservation reservation = new Reservation();
		reservation.setPerson(reservation.getPerson());
		reservation.setChair(ChairDao.getChairById(chair));
		reservation.setShow(ShowDao.getShowById(show));
		reservation.setUser(UserDao.getUserById(user));
		if (ReservationDao.addReservation(reservation)) {
			System.out.println("show added");

		} else {
			System.out.println("show was not added");
		}
		return null;
	}*/
	public String deleteReservation(String id) {

		if (ReservationDao.deleteReservation(id)) {
			System.out.println("show removed");
			updateReservation();
		} else {
			System.out.println("show was not removed");
		}
		return null;
	}
	public void updateReservation() {
		reservations = ReservationDao.getReservationFromDb();
	}
	
	public void addReservation() {
		Reservation reservation = new Reservation();
		
		reservation.setPerson("prove");
		reservation.setChair(ChairDao.getChairById(Integer.valueOf(chair)));
		reservation.setShow(ShowDao.getShowById(showBean.getShow1().getId()));
		reservation.setUser(userBean.getUser());
		if (ReservationDao.addReservation(reservation)) {
			System.out.println("reservation added");

		} else {
			System.out.println("reservation was not added");
		}
	}
	public String choose(String showId) {
		System.out.println(showId);
		//Reservation reservation1 = new Reservation();
		//show= Integer.valueOf(showId);
		reservation.setShow(ShowDao.getShowById(Integer.valueOf(showId)));
		//Show show= ShowDao.getShowById(Integer.valueOf(showId));
		return "client_reservation?faces-redirect=true";
		
	}
}
