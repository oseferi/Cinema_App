package com.al.akademia.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.Category;
import com.al.akademia.utility.HibernateUtil;

public class CategoryDao {
	
	private static Transaction transObj = null;

	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession(); 
	
	public static Category getCategoryById(int categoryId) {
	    
	    Category category = null;
	    try {
			
	    	
			transObj = sessionObj.beginTransaction();

	        category = (Category) sessionObj.get(Category.class, categoryId);
	        sessionObj.merge(category);
			transObj.commit();
			
		} 
	    catch (Exception exceptionObj) {
			transObj.rollback();
			exceptionObj.printStackTrace();
		} 
	    
	    return category;
	}
	public static List<Category> getAllCategories(){

		try {


			
			transObj = sessionObj.beginTransaction();

			List<Category> categories = sessionObj.createQuery("from Category ").getResultList();
			
			transObj.commit();
			return categories;
			
		} catch (Exception exceptionObj) {
			transObj.rollback();
			exceptionObj.printStackTrace();
			return null;

		} 
	}
}
