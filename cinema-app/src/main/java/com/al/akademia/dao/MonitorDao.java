package com.al.akademia.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.Monitor;
import com.al.akademia.utility.HibernateUtil;

public class MonitorDao {
	private static Transaction transObj = null;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();
	
	
	public static boolean addMonitor(Monitor monitor) {
		try {
			
			sessionObj.save(monitor);
			System.out.println("Monitor was added");
			return true;
			
		}catch (Exception e) {
			System.out.println("Monitor was not added");
			e.printStackTrace();
			return false;	
			
		} 
	}
	
	public static boolean deleteMonitor(int monitorId) {
		try {
			Monitor monitorToBeDeleted = sessionObj.load(Monitor.class, monitorId);
			sessionObj.delete(monitorToBeDeleted);
			System.out.println("Monitor was deleted successfully");
			return true;
		} catch (Exception e) {
			System.out.println("Monitor failed to be deleted");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean updateMonitor(int monitorId, Monitor monitor) {
		
		try {
			transObj = sessionObj.beginTransaction();

			Monitor monitorToBeUpdated = sessionObj.get(Monitor.class, monitorId);
			monitorToBeUpdated.setName(monitor.getName());
			monitorToBeUpdated.setDescription(monitor.getDescription());
			monitorToBeUpdated.setRoom(monitor.getRoom());
			
			sessionObj.evict(monitorToBeUpdated);
			monitor = (Monitor) sessionObj.merge(monitorToBeUpdated);
			sessionObj.saveOrUpdate(monitor);
			transObj.commit();
			System.out.println("Monitor was updated successfully");
			return true;
		} catch (Exception e) {
			transObj.rollback();
			System.out.println("Monitor was not updated");
			e.printStackTrace();
			return false;
		}
	}
	public static Monitor getMonitorById(int monitorId) {
		
		Monitor monitor = null;
		try {

			//transObj = sessionObj.beginTransaction();

			monitor = (Monitor) sessionObj.get(Monitor.class, monitorId);
			sessionObj.merge(monitor);
			//transObj.commit();
			System.out.println(monitor.toString());
		} catch (Exception exceptionObj) {
			//transObj.rollback();
			exceptionObj.printStackTrace();
		} 
		return monitor;
	}

	public static List<Monitor> getAllMonitors() {
		Session sessionObj = HibernateUtil.getSessionFactory().openSession();
		try {

			List<Monitor> monitors = sessionObj.createQuery("from Monitor ").getResultList();

			return monitors;

		} catch (Exception exceptionObj) {
		
			exceptionObj.printStackTrace();
			return null;

		}

	}
}