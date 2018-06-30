package com.al.akademia.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.Cinema;
import com.al.akademia.utility.HibernateUtil;

public class CinemaDao {
	private static Transaction transObj;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();
	
	
	public static Cinema getThisCinema() {
		Cinema cinemaTemp = null;
		try {
			cinemaTemp = (Cinema) sessionObj.get(Cinema.class, 32);
	        cinemaTemp =(Cinema) sessionObj.merge(cinemaTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cinemaTemp;
	}
}
