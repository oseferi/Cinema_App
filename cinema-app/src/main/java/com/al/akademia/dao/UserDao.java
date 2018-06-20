package com.al.akademia.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.Role;
import com.al.akademia.entitete.User;
import com.al.akademia.utility.HibernateUtil;

public class UserDao {

	
	private static Transaction transObj;
	
	public static void addUser(User user) {

		try {
			Transaction transObj;
			Session sessionObj = HibernateUtil.getSessionFactory().openSession();
			transObj = sessionObj.beginTransaction();
			transObj.commit();
			sessionObj.save(user);

		} catch (Exception exceptionObj) {

			exceptionObj.printStackTrace();

		} finally {
		

		}

	}

	public static void deleteUser(int id) {

		try {
			Transaction transObj;
			Session sessionObj = HibernateUtil.getSessionFactory().openSession();
	
			transObj = sessionObj.beginTransaction();

			Query query = sessionObj.createNativeQuery("delete from user where id =:id");
			query.setParameter("id", id);
			query.executeUpdate();
			transObj.commit();
		
		} catch (Exception exceptionObj) {

			exceptionObj.printStackTrace();

		} 
	}

	public static void updateUser(String username, String password, String firstName, String surName, String email,
			Role role, int id) {

		try {
			Transaction transObj;
			Session sessionObj = HibernateUtil.getSessionFactory().openSession();

			transObj = sessionObj.beginTransaction();

			Query query = sessionObj.createNativeQuery(
					"update user SET username=?, password=?, surname=?, email=?, role=? where id = ? ");
			query.setParameter(1, username);
			query.setParameter(2, password);
			query.setParameter(3, firstName);
			query.setParameter(4, surName);
			query.setParameter(5, email);
			query.setParameter(6, role);
			query.setParameter(7, id);

			query.executeUpdate();
			transObj.commit();
		} catch (Exception exceptionObj) {

			exceptionObj.printStackTrace();

		} finally {
			

		}
	}

	public static User existUser(String username, String password) {
		Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        User user=null;
        try {
            Transaction transObj;
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
		Session sessionObj = HibernateUtil.getSessionFactory().openSession();
		try {
			// sessionObj = HibernateUtil.getSessionFactory().openSession();
			transObj = sessionObj.beginTransaction();

			List<User> users = sessionObj.createQuery("from User ").getResultList();
			transObj.commit();
			return users;

		} catch (Exception exceptionObj) {
			transObj.rollback();
			exceptionObj.printStackTrace();
			return null;

		}
	}
	public static User getUserById(int userId) {
		Session sessionObj = HibernateUtil.getSessionFactory().openSession();
		User user = null;
		try {

			transObj = sessionObj.beginTransaction();

			user= (User) sessionObj.get(User.class, userId);
			sessionObj.merge(user);
			transObj.commit();

		} catch (Exception exceptionObj) {

			exceptionObj.printStackTrace();
			transObj.rollback();
		} 
		return user;
	}
	
}
