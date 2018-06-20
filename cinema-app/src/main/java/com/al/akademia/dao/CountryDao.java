package com.al.akademia.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.Country;
import com.al.akademia.utility.HibernateUtil;


public class CountryDao {

	private static Transaction transObj = null;

	private static Session sessionObj=HibernateUtil.getSessionFactory().openSession(); ;
	
	public static Country getCountryById(int countryId) {
	    
	    Country country = null;
	    try {
	    	//sessionObj = HibernateUtil.getSessionFactory().openSession();
	    	transObj = sessionObj.beginTransaction();
	    	
	    	country = (Country) sessionObj.get(Country.class, countryId);
	    	transObj.commit();
	    	} 
	    catch (Exception e) {
	    	
	    	transObj.rollback();
	    	e.printStackTrace();
	       
	    	} 
	    
	    return country;
	}
	
	public static List<Country> getAllCountries(){

		try {
		
	    	transObj = sessionObj.beginTransaction();
			List<Country> countries = sessionObj.createQuery("from Country ").getResultList();
			transObj.commit();
			return countries;
			} 
		catch (Exception exceptionObj) {

	    	transObj.rollback();
			exceptionObj.printStackTrace();
			return null;
			
			} 
		
	}
}
