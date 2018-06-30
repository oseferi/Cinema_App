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
			
	        category = (Category) sessionObj.get(Category.class, categoryId);
	        
		} 
	    catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} 
	    
	    return category;
	}
	public static List<Category> getAllCategories(){

		try {
			List<Category> categories = sessionObj.createQuery("from Category ").getResultList();
			return categories;
			
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
			return null;

		} 
	}
}
