package com.al.akademia.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.al.akademia.entitete.Room;
import com.al.akademia.utility.HibernateUtil;

public class RoomDao {
	private static Transaction transObj;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();
	
	public static boolean addRoom(Room room) {

		try {
			transObj = sessionObj.beginTransaction();
			sessionObj.merge(room);
			transObj.commit();
			return true;
			}
		
		catch (Exception exceptionObj) {
		
			transObj.rollback();
			exceptionObj.printStackTrace();
			return false;
			}

	}

	public static boolean deleteRoom(int id) {

		try {

			transObj = sessionObj.beginTransaction();

			Query query = sessionObj.createNativeQuery("delete from room where id =:id");
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

	public static boolean updateRoom(int id,Room room) {

		try {
			Room roomToBeUpdated = sessionObj.get(Room.class, id);
			//roomToBeUpdated.setCinema(room.getCinema());
			roomToBeUpdated.setDescription(room.getDescription());
			roomToBeUpdated.setName(room.getName());
			transObj = sessionObj.beginTransaction();
			//sessionObj.evict(roomToBeUpdated);
			
			sessionObj.merge(roomToBeUpdated);
			
			transObj.commit();
			return true;
			
			
		} catch (Exception exceptionObj) {
			transObj.rollback();
			exceptionObj.printStackTrace();
			return false;
		} 
	}
	
	public static Room getRoomById(int roomId) {
		Room room = null;
		try {
			
			transObj = sessionObj.beginTransaction();
			room = sessionObj.get(Room.class, roomId);
	        sessionObj.merge(room);
			transObj.commit();
			
		} catch (Exception e) {
			transObj.rollback();
			e.printStackTrace();
		}
		return room;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Room> getAllRooms(){

		try {
			transObj = sessionObj.beginTransaction();
			List<Room> rooms = sessionObj.createQuery("from Room ").getResultList();
			transObj.commit();
			
			return rooms;
			
		} catch (Exception exceptionObj) {
			transObj.rollback();
			exceptionObj.printStackTrace();
			
			return null;

		} 
	}

}

