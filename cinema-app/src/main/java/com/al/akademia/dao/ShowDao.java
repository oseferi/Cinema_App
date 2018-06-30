package com.al.akademia.dao;

import java.util.Date;
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
			Date now = new Date();
			if (show.getStart().after(now)) {
				String queryMbivendosje1 = "SELECT s.id, s.movie.id, s.start, s.monitor.id, s.movie.duration "
						+ "FROM Show s " + "INNER JOIN s.movie m " + "ON s.monitor.id=:monitor "
						+ "WHERE (ADDTIME(:start,SEC_TO_TIME(:duration*60))>=s.start and ADDTIME(:start,SEC_TO_TIME(:duration*60))<=ADDTIME(s.start,SEC_TO_TIME(m.duration*60))) OR (:start>=s.start AND :start<=ADDTIME(s.start,SEC_TO_TIME(m.duration*60)))";
				
				Query mbivendosjeShfaqesh = sessionObj.createQuery(queryMbivendosje1);
				mbivendosjeShfaqesh.setParameter("monitor", show.getMonitor().getId());
				mbivendosjeShfaqesh.setParameter("start", show.getStart());
				System.out.println(show.getStart());
				mbivendosjeShfaqesh.setParameter("duration", show.getMovie().getDuration());
				List<Show> shows = mbivendosjeShfaqesh.getResultList();

				if (shows.isEmpty()) {
					System.out.println("u shtua");
					sessionObj.save(show);
					return true;
				} else
					System.out.println("Nuk u shtua");

			}
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();

		}
		return false;
	}

	public static List<Show> getShowsFromDB() {

		try {
			List<Show> shows = sessionObj.createQuery("from Show ").getResultList();
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

			showToBeUpdated.setMonitor(show.getMonitor());
			showToBeUpdated.setMovie(show.getMovie());
			showToBeUpdated.setStart(show.getStart());
			Date now = new Date();
			if (showToBeUpdated.getStart().after(now)) {
				String queryMbivendosje1 = "SELECT s.id, s.movie.id, s.start, s.monitor.id, s.movie.duration "
						+ "FROM Show s " + "INNER JOIN s.movie m " + "ON s.monitor.id=:monitor "
						+ "WHERE (ADDTIME(:start,SEC_TO_TIME(:duration*60))>=s.start and ADDTIME(:start,SEC_TO_TIME(:duration*60))<=ADDTIME(s.start,SEC_TO_TIME(m.duration*60))) OR (:start>=s.start AND :start<=ADDTIME(s.start,SEC_TO_TIME(m.duration*60)))";

				Query mbivendosjeShfaqesh = sessionObj.createQuery(queryMbivendosje1);
				mbivendosjeShfaqesh.setParameter("monitor", showToBeUpdated.getMonitor().getId());
				mbivendosjeShfaqesh.setParameter("start", showToBeUpdated.getStart());
				System.out.println(show.getStart());
				mbivendosjeShfaqesh.setParameter("duration", showToBeUpdated.getMovie().getDuration());

				List<Show> shows = mbivendosjeShfaqesh.getResultList();

				if (shows.isEmpty()) {
					transObj = sessionObj.beginTransaction();
					sessionObj.evict(showToBeUpdated);
					Show showUpdated= (Show) sessionObj.merge(showToBeUpdated);
					sessionObj.saveOrUpdate(showUpdated);
					transObj.commit();
					System.out.println("Show updated succesfulsly");
					return true;
				} else
					System.out.println("Nuk u shtua");

			}
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();

		}
		return false;

	}

	public static Show getShowById(int showId) {

		Show show = null;
		try {
			show = (Show) sessionObj.get(Show.class, showId);

		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		}
		return show;
	}

	public static List<Show> getShowFromDb1(int movieId) {

		try {
			Query query = sessionObj.createQuery("from Show where movie_id =:id");
			query.setParameter("id", movieId);

			List<Show> shows = query.getResultList();
			return shows;
		} catch (Exception exceptionObj) {

			exceptionObj.printStackTrace();
			return null;
		}

	}
}
