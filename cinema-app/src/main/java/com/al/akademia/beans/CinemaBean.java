package com.al.akademia.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.al.akademia.dao.CinemaDao;
import com.al.akademia.dao.RoomDao;
import com.al.akademia.entitete.Cinema;
import com.al.akademia.entitete.Room;

@ManagedBean(name="cinemaBean")
@SessionScoped
public class CinemaBean {

	private Cinema cinema;
	private Room room;
	private Room roomToBeUpdated;
	private List<Room> rooms;
	private String successMessage;
	
	public CinemaBean() {
		cinema = CinemaDao.getThisCinema();
		room = new Room();
		roomToBeUpdated = new Room();
		rooms = RoomDao.getAllRooms();
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	
	public Room getRoomToBeUpdated() {
		return roomToBeUpdated;
	}

	public void setRoomToBeUpdated(Room roomToBeUpdated) {
		this.roomToBeUpdated = roomToBeUpdated;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	/*****************************************
	 * M A N I P U L A T I O N     L O G I C
	 ****************************************/
	
	public String addRoom() {
		Room newRoom = new Room();
		newRoom.setName(room.getName());
		newRoom.setDescription(room.getDescription());
		newRoom.setCinema(cinema);
		//newRoom.setCinema(rooms.get(0).getCinema());
		if(RoomDao.addRoom(newRoom)) {
			System.out.println("room added");
			updateRooms();
		}
		else {
			System.out.println("room was not added");
		}
		room.setName(null);
		room.setDescription(null);
		return null;
	}

	public String deleteRoom(String id) {
		
		if(RoomDao.deleteRoom(Integer.valueOf(id))) {
			System.out.println("room removed");
			updateRooms();
		}
		else {
			System.out.println("room was not removed");
		}
		return null;
	}	
	
	public void updateRooms() {
		rooms = RoomDao.getAllRooms();
	}
	
	public String editAction(int roomId) {
		roomToBeUpdated = RoomDao.getRoomById(roomId);
		return "edit_room";
	}
	
	public String updateAction(int id,Room room) {
		
		if(RoomDao.updateRoom(id, room)) {
			successMessage="Room with Id "+ String.valueOf(id) + " updated succesfully";
			updateRooms();
			return "response_room";
		}
		else {
			successMessage="Room with Id "+ String.valueOf(id) + "was not updated succesfully";
			return "response_room";
		}
	
	}
}
