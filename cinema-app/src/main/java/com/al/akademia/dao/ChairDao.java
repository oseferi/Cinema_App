package com.al.akademia.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.Chair;
import com.al.akademia.utility.HibernateUtil;

public class ChairDao {
	private static Transaction transObj;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();
	
	public static boolean addChair(Chair chair) {
		try {
			sessionObj.save(chair);
			System.out.println("Chair was added");
			return true;
		}catch (Exception e) {
			System.out.println("Chair was not added");
			e.printStackTrace();
			return false;	
		} 
	}
	
	public static boolean addChairs(List<Chair> chairs) {
		try {
			transObj=sessionObj.beginTransaction();
			int i = 1;
			for(Chair chair :chairs) {
				System.out.println(chair);
				sessionObj.save(chair);
				System.out.println(i);
				if(i%20==0) {
					sessionObj.flush();
					sessionObj.clear();
				}
				i++;
				
			}
			transObj.commit();
			System.out.println("Chairs were added");
			return true;
		}catch (Exception e) {
			transObj.rollback();
			System.out.println("Chairs were not added");
			e.printStackTrace();
			return false;	
		} 
	}
	
	public static boolean deleteChair(int chairId) {
		try {
			Chair chairToBeDeleted = sessionObj.load(Chair.class, chairId);
			sessionObj.delete(chairToBeDeleted);
			System.out.println("Chair was deleted successfully");
			return true;
		} catch (Exception e) {
			System.out.println("Chair failed to be deleted");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean updateChair(int chairId, Chair chairToBeUpdated) {
		
		try {
			sessionObj.evict(chairToBeUpdated);
			sessionObj.merge(chairToBeUpdated);
			System.out.println("Chair was updated successfully");
			return true;
		} catch (Exception e) {
			System.out.println("Chair was not updated");
			e.printStackTrace();
			return false;
		}
	}
	public static List<Chair> getChairsFromDB() {
	
		try {
			

			List<Chair> chairs = sessionObj.createQuery("from Chair ").getResultList();
			return chairs;
			
		} catch (Exception exceptionObj) {
			//transObj.rollback();
			exceptionObj.printStackTrace();
			return null;

		} 

	}
	public static Chair getChairById(int chairId) {
		
		Chair chair = null;
		try {


			chair= (Chair) sessionObj.get(Chair.class, chairId);
			
		} catch (Exception exceptionObj) {

			exceptionObj.printStackTrace();
		} 
		return chair;
	}
	
	public static int getChairsOfMonitor(int monitorId) {
		try {
			//transObj=sessionObj.beginTransaction();
			Query query = sessionObj.createNativeQuery("Select count(ch.id) from Chair ch  where ch.monitor_id = ?");
			query.setParameter(1, monitorId);
			return  ((BigInteger)query.getSingleResult()).intValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
	public static List<Chair> getAllChairsOfMonitor(int monitorId) {
		try {
			System.out.println("u futem");
			System.out.println(monitorId);
			//transObj=sessionObj.beginTransaction();
			Query query = sessionObj.createQuery("from Chair c where c.monitor.id = :monitor");
			query.setParameter("monitor", monitorId);
			List<Chair> chairs = query.getResultList();
			for(Chair chair: chairs) {
				System.out.println("nuk u futem");
				System.out.println(chair.toString());
			}
			if(chairs.isEmpty()) {
				System.out.println("bosh");
			}
			System.out.println("u futem");

			return chairs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
