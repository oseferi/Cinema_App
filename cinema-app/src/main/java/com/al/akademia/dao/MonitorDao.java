package com.al.akademia.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.Monitor;
import com.al.akademia.utility.HibernateUtil;

public class MonitorDao {
	private static Transaction transObj = null;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();

	public static Monitor getMonitorById(int monitorId) {
		
		Monitor monitor = null;
		try {

			transObj = sessionObj.beginTransaction();

			monitor = (Monitor) sessionObj.get(Monitor.class, monitorId);
			sessionObj.merge(monitor);
			transObj.commit();
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
