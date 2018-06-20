package com.al.akademia.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.Show;
import com.al.akademia.utility.HibernateUtil;

public class ShowDao {
	private static Transaction transObj;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();

	public static boolean addShow(Show show) {

		try {
			transObj = sessionObj.beginTransaction();
	
			/*String queryMbivendosje = "Select show FROM Show show,Movie movie "
					+ " WHERE ADDDATE(show.start,SEC_TO_TIME(movie.duration*60))>=:start and DATEDIFF(show.start,SEC_TO_TIME(:duration*60))<=:start ";
			Query mbivendosjeShfaqesh = sessionObj.createQuery(queryMbivendosje);
			mbivendosjeShfaqesh.setParameter("start", show.getStart());
			mbivendosjeShfaqesh.setParameter("duration", show.getMovie().getDuration());*/

			sessionObj.save(show);
			transObj.commit();

			return true;

		} catch (Exception exceptionObj) {
			if (transObj != null) {
				transObj.rollback();
			}
			exceptionObj.printStackTrace();
			return false;
		} 
	}

	public static List<Show> getShowsFromDB() {

		try {
			transObj = sessionObj.beginTransaction();
			List<Show> shows = sessionObj.createQuery("from Show ").getResultList();
			transObj.commit();
			return shows;

		} catch (Exception exceptionObj) {

			exceptionObj.printStackTrace();
			return null;

		}

	}

	public static boolean deleteShow(int id) {

		try {

			transObj = sessionObj.beginTransaction();

			Query query = sessionObj.createNativeQuery("delete from show1 where id =:id");
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



	public static boolean updateShow(int id, Show show) {

		try {

			Show showToBeUpdated = sessionObj.get(Show.class, id);

			showToBeUpdated.setStart(show.getStart());
			showToBeUpdated.setMonitor(show.getMonitor());
			showToBeUpdated.setMovie(show.getMovie());

			transObj = sessionObj.beginTransaction();
			
			sessionObj.merge(showToBeUpdated);
			
			transObj.commit();
			System.out.println("Show updated succesfulsly");
			return true;

		} catch (Exception exceptionObj) {

			exceptionObj.printStackTrace();
			return false;
		} 
	}

	public static Show getShowById(int showId) {

		Show show = null;
		try {
			transObj = sessionObj.beginTransaction();
			show = (Show) sessionObj.get(Show.class, showId);
			sessionObj.merge(show);
			transObj.commit();

		} catch (Exception exceptionObj) {

			exceptionObj.printStackTrace();
			transObj.rollback();
		}
		return show;
	}
	public static List<Show> getShowFromDb1(int movieId) {
	
		try {

			transObj = sessionObj.beginTransaction();

			Query query = sessionObj.createQuery("from Show where movie_id =:id");
			query.setParameter("id", movieId);

			List<Show >shows =query.getResultList();
			transObj.commit();
			return shows;
		} catch (Exception exceptionObj) {
			
			exceptionObj.printStackTrace();
			transObj.rollback();
			return null;
		} 
		
	}
}
