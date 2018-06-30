package com.al.akademia.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.User;
import com.al.akademia.utility.HibernateUtil;

public class UserDao {

	
	private static Transaction transObj;
	private static 	Session sessionObj = HibernateUtil.getSessionFactory().openSession();

	public static void addUser(User user) {

		try {
			sessionObj.save(user);

		} catch (Exception exceptionObj) {

			exceptionObj.printStackTrace();

		} 
	}

	public static void deleteUser(int id) {
		try {
			transObj = sessionObj.beginTransaction();

			Query query = sessionObj.createNativeQuery("delete from user where id =:id");
			query.setParameter("id", id);
			query.executeUpdate();
			transObj.commit();
		
		} catch (Exception exceptionObj) {
			transObj.rollback();
			exceptionObj.printStackTrace();

		} 
	}


	public static User existUser(String username, String password) {
		Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        User user=null;
        try {
            System.out.println("Finding the user...");

            transObj = sessionObj.beginTransaction();
            Query query = sessionObj.createQuery("From User u where u.username=:user and u.password=:pass");
            query.setParameter("user", username);
            query.setParameter("pass", password);
            transObj.commit();
            user = (User)query.getSingleResult();
            return user;

        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        } 
        return null;
	}
	public static List<User> getUsersFromDB() {
		try {
			List<User> users = sessionObj.createQuery("from User ").getResultList();
			return users;

		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
			return null;

		}
	}
	public static User getUserById(int userId) {
		User user = null;
		try {
			user= (User) sessionObj.get(User.class, userId);
			return (User)sessionObj.merge(user);
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} 
		return user;
	}
}
