package com.al.akademia.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.Reservation;
import com.al.akademia.entitete.Show;
import com.al.akademia.utility.HibernateUtil;

public class ReservationDao {

	private static Transaction transObj;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();

	public static boolean addReservation(Reservation reservation) {
		

		try {
			//transObj = sessionObj.beginTransaction();
			sessionObj.save(reservation);
			//transObj.commit();
			return true;
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
			return false;
		} 
	}
	
	public static boolean deleteReservation(String id) {
		Session sessionObj = HibernateUtil.getSessionFactory().openSession();

		try {

			transObj = sessionObj.beginTransaction();

			Query query = sessionObj.createNativeQuery("delete from reservation where id =:id");
			query.setParameter("id", id);
			query.executeUpdate();

			transObj.commit();
			return true;
		} catch (Exception exceptionObj) {
			transObj.rollback();
			exceptionObj.printStackTrace();
			return false;
		} 
	}

	
	public static List<Reservation> getReservationFromDb() {
		Session sessionObj = HibernateUtil.getSessionFactory().openSession();

		try {

			transObj = sessionObj.beginTransaction();
			List<Reservation> reservations = sessionObj.createQuery("from Reservation ").getResultList();

			transObj.commit();
			for (Reservation reservation : reservations) {
				System.out.println(reservation.toString());
			}
			return reservations;
		} catch (Exception exceptionObj) {

			exceptionObj.printStackTrace();
			return null;
		} finally {

		}
	}

	public static Reservation getReservationById(int reservationId) {
		Session sessionObj = HibernateUtil.getSessionFactory().openSession();

		Reservation reservation = null;
		try {

			transObj = sessionObj.beginTransaction();

			reservation = (Reservation) sessionObj.get(Reservation.class, reservationId);
			sessionObj.merge(reservation);
			transObj.commit();

		} catch (Exception exceptionObj) {

			exceptionObj.printStackTrace();
			transObj.rollback();
		} 
		return reservation;
	}
	public static void main(String[] args) {
		getReservationFromDb();
	}
	public static Show getReservationFromDb(int showId) {
		Session sessionObj = HibernateUtil.getSessionFactory().openSession();

		try {

			transObj = sessionObj.beginTransaction();
			Query query = sessionObj.createQuery("from Show where id =:id");
			query.setParameter("id", showId);

			Show show = (Show) query.getSingleResult();

			return show;
		} catch (Exception exceptionObj) {
			
			exceptionObj.printStackTrace();
			transObj.rollback();
			return null;
		}
	}
}
