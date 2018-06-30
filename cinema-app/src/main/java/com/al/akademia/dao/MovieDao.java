package com.al.akademia.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.Movie;
import com.al.akademia.utility.HibernateUtil;

public class MovieDao {
	private static Transaction transObj = null;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();

	public static boolean addMovie(Movie movie) {

		try {
			sessionObj.save(movie);
			System.out.println("Movie added");
			return true;
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
			return false;
		}
	}

	public static boolean deleteMovie(int id) {

		try {

			transObj = sessionObj.beginTransaction();
			Query query = sessionObj.createNativeQuery("delete from movie where id =:id");
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

	public static List<Movie> getMoviesFromDB() {

		try {
			List<Movie> movies = sessionObj.createQuery("from Movie ").getResultList();
			return movies;
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
			return null;
		}

	}

	public static boolean updateMovie(int id, Movie movie) {

		try {
			transObj = sessionObj.beginTransaction();

			Movie movieToBeUpdated = sessionObj.get(Movie.class, id);
			movieToBeUpdated.setTitle(movie.getTitle());
			movieToBeUpdated.setDescription(movie.getDescription());
			movieToBeUpdated.setCategory(movie.getCategory());
			movieToBeUpdated.setDirector(movie.getDirector());
			movieToBeUpdated.setYear(movie.getYear());
			movieToBeUpdated.setRating(movie.getRating());
			movieToBeUpdated.setCountry(movie.getCountry());

			sessionObj.evict(movieToBeUpdated);
			Movie movieToUpdate = (Movie) sessionObj.merge(movieToBeUpdated);
			sessionObj.saveOrUpdate(movieToUpdate);
			transObj.commit();
			System.out.println("Movie updated succesfully");
			return true;

		} catch (Exception exceptionObj) {
			transObj.rollback();
			exceptionObj.printStackTrace();
			return false;
		}
	}

	public static Movie getMovieById(int movieId) {

		Movie movie = null;
		try {
			movie = (Movie) sessionObj.get(Movie.class, movieId);
			return (Movie)sessionObj.merge(movie);

		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
			return null;
		}
		 
	}
}
