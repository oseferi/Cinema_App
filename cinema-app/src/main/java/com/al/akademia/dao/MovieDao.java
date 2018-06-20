package com.al.akademia.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.Movie;
import com.al.akademia.utility.HibernateUtil;

public class MovieDao {
	private static Transaction transObj = null;
	private static Session sessionObj= HibernateUtil.getSessionFactory().openSession(); 
	
	public static boolean addMovie(Movie movie) {
		
		try {
	
			transObj = sessionObj.beginTransaction();

			sessionObj.merge(movie);
			System.out.println("Movie added");
			
			transObj.commit();
			
			return true;
		} catch (Exception exceptionObj) {
			transObj.rollback();
			exceptionObj.printStackTrace();
			return false;
		} finally {
			/*if(sessionObj.isOpen() && sessionObj!=null) {
				sessionObj.close();
			}*/
			
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

	

	


	public static List<Movie> getMoviesFromDB(){
		
		try {
			//sessionObj = HibernateUtil.getSessionFactory().openSession();
			transObj = sessionObj.beginTransaction();

			
			List<Movie> movies = sessionObj.createQuery("from Movie ").getResultList();
			transObj.commit();
			return movies;
			
		
			} catch (Exception exceptionObj) {
				transObj.rollback();
				exceptionObj.printStackTrace();
				return null;
			
			} finally {
				/*if(sessionObj.isOpen() && sessionObj!=null) {
					sessionObj.close();
				}*/
				
			}
		
	}
	
	
	public static boolean updateMovie(int id,Movie movie) {

		try {

			Movie movieToBeUpdated= sessionObj.get(Movie.class,id);
			
			movieToBeUpdated.setTitle(movie.getTitle());
			
			movieToBeUpdated.setDescription(movie.getDescription());
			
			movieToBeUpdated.setCategory(movie.getCategory());
			
			movieToBeUpdated.setDirector(movie.getDirector());
			
			movieToBeUpdated.setYear(movie.getYear());

			movieToBeUpdated.setRating(movie.getRating());
			
			movieToBeUpdated.setCountry(movie.getCountry());
			
			transObj = sessionObj.beginTransaction();
			sessionObj.evict(movieToBeUpdated);
			
			sessionObj.merge(movieToBeUpdated);
			
			transObj.commit();
			System.out.println("Movie updated succesfulsly");
			return true;
			
		} catch (Exception exceptionObj) {
			transObj.rollback();
			exceptionObj.printStackTrace();
			return false;
		} finally {
			
			
		}
	}
	
	public static Movie getMovieById(int movieId) {
	    
	    Movie movie = null;
	    try {
			
			transObj = sessionObj.beginTransaction();

	        movie  = (Movie) sessionObj.get(Movie.class, movieId);
	        sessionObj.merge(movie);
			transObj.commit();
			
		} 
	    catch (Exception exceptionObj) {
	    	
	    	transObj.rollback();
			exceptionObj.printStackTrace();

			transObj.rollback();

		} 
	    return movie;
	}
}
