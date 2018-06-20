package com.al.akademia.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.Chair;
import com.al.akademia.utility.HibernateUtil;

public class ChairDao {
	private static Transaction transObj;
	
	public static List<Chair> getChairsFromDB() {
		Session sessionObj = HibernateUtil.getSessionFactory().openSession();
		try {
			
			transObj = sessionObj.beginTransaction();

			List<Chair> chairs = sessionObj.createQuery("from Chair ").getResultList();
			transObj.commit();
			for (Chair chair : chairs) {
				System.out.println(chair.toString());
			}
			return chairs;
			
		} catch (Exception exceptionObj) {
			transObj.rollback();
			exceptionObj.printStackTrace();
			return null;

		} finally {
			/*
			 * if(sessionObj.isOpen() && sessionObj!=null) { sessionObj.close(); }
			 */

		}

	}
	public static Chair getChairById(int chairId) {
		Session sessionObj = HibernateUtil.getSessionFactory().openSession();
		Chair chair = null;
		try {

			// sessionObj = HibernateUtil.getSessionFactory().openSession();
			transObj = sessionObj.beginTransaction();

			chair= (Chair) sessionObj.get(Chair.class, chairId);
			sessionObj.merge(chair);
			transObj.commit();
			
		} catch (Exception exceptionObj) {

			exceptionObj.printStackTrace();
			transObj.rollback();
		} finally {
			/*
			 * if(sessionObj.isOpen() && sessionObj!=null) { sessionObj.close(); }
			 */

		}
		return chair;
	}
	
}
