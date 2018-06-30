package com.al.akademia.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.al.akademia.dao.CinemaDao;
import com.al.akademia.dao.RoomDao;
import com.al.akademia.entitete.Cinema;
import com.al.akademia.entitete.Room;

@ManagedBean(name="cinemaBean")
@ViewScoped
public class CinemaBean {

	private Cinema cinema;
	private Room room;
	private Room roomToBeUpdated;
	private List<Room> rooms;
	private String successMessage;
	private String script;
	
	
	public CinemaBean() {
		cinema = CinemaDao.getThisCinema();
		room = new Room();

		rooms = RoomDao.getAllRooms();
		if(FacesContext.getCurrentInstance().getExternalContext().getFlash().get("id")!=null) {
			roomToBeUpdated = RoomDao.getRoomById(Integer.valueOf(String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getFlash().get("id"))));
		}else {
			roomToBeUpdated = new Room();
			}
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

	
	
	
	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	/*****************************************
	 * M A N I P U L A T I O N     L O G I C
	 ****************************************/
	
	public String addRoom() {
		Room newRoom = new Room();
		newRoom.setName(room.getName());
		newRoom.setDescription(room.getDescription());
		newRoom.setCinema(cinema);
		if(RoomDao.addRoom(newRoom)) {
			setScript("alert('Room was added succesfully.');window.location.replace(window.location.href.replace('add_room','rooms'));");
			System.out.println("room added");
			updateRooms();
		}
		else {
			setScript("alert('Room was NOT added.If the problem persists please contact the admnistrator');");
			System.out.println("room was not added");
		}
		room.setName(null);
		room.setDescription(null);
		return null;
	}

	public String deleteRoom(String id) {
		
		if(RoomDao.deleteRoom(Integer.valueOf(id))) {
			setScript("alert('Room was deleted succesfully.');");
			System.out.println("room removed");
			updateRooms();
		}
		else {
			setScript("alert('Room was NOT deleted.If the problem persists please contact the admnistrator.');");
			System.out.println("room was not removed");
		}
		return null;
	}	
	
	public void updateRooms() {
		rooms = RoomDao.getAllRooms();
	}
	
	public String editAction(int roomId) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("id", roomId );
		return "edit_room?faces-redirect=true";
	}
	
	public String updateAction(int id,Room room) {
		
		if(RoomDao.updateRoom(id, room)) {
			setScript("alert('Room was updated succesfully.');window.location.replace(window.location.href.replace('edit_room','rooms'));");
			updateRooms();
			return null ;
		}
		else {
			setScript("alert('Room was could NOT be updated.If the problem persists please contact the admnistrator');");
			return null;
		}
	
	}
}
