package com.al.akademia.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.al.akademia.dao.ChairDao;
import com.al.akademia.dao.MonitorDao;
import com.al.akademia.dao.RoomDao;
import com.al.akademia.entitete.Chair;
import com.al.akademia.entitete.Monitor;
import com.al.akademia.entitete.Room;

@ManagedBean(name = "monitorBean")
@ViewScoped
public class MonitorBean {
	private List<Monitor> monitors;
	private Monitor monitor;
	private List<Room> rooms;
	private int roomId;
	private int numberOfRows;
	private int numberOfColumns;
	private String script;

	
	public MonitorBean() {

		monitor = new Monitor();
		monitors = MonitorDao.getAllMonitors();
		rooms = RoomDao.getAllRooms();
	}
	public List<Monitor> getMonitors() {
		return monitors;
	}
	public void setMonitors(List<Monitor> monitors) {
		this.monitors = monitors;
	}
	public Monitor getMonitor() {
		return monitor;
	}
	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	public int getNumberOfRows() {
		return numberOfRows;
	}
	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	public int getNumberOfColumns() {
		return numberOfColumns;
	}
	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	/**************************************************
	 *D A T A    M A N I P U L A T I O N    L O G I C
	 *************************************************/
	/*public String addMonitor() {
		Monitor newMonitor = new Monitor();
		List<Chair> newChairs = new ArrayList<Chair>();
		boolean unfinished = false;
		int index = 1;
		newMonitor.setName(monitor.getName());
		newMonitor.setDescription(monitor.getDescription());
		newMonitor.setRoom(RoomDao.getRoomById(roomId));
		if(MonitorDao.addMonitor(newMonitor)) {
			System.out.println("Monitor was added");		
			for(int i = 0;i < numberOfRows;i++) {
				for(int j= 0 ;j<numberOfColumns;j++) {
					if(!newChairs.add(new Chair(index,newMonitor,"{'Row' :"+String.valueOf(i)+", 'Coloumn' :"+String.valueOf(j)+"}"))) {
						unfinished=true;
						break;
					}
				}
			}
			if(!unfinished) {
				if(ChairDao.addChairs(newChairs)) {
					System.out.println("Chairs were added");
					setScript("alert('Monitor "+newMonitor.getName()+" with "+String.valueOf(numberOfColumns*numberOfRows)+" chairs was added succesfully.');");

				}
				else {
					System.out.println("Chairs not added added");
					setScript("alert('Monitor "+newMonitor.getName()+" with "+String.valueOf(numberOfColumns*numberOfRows)+" chairs was NOT added. Please contact the administrator if the problem persists.');");
					
				}
			}else {
				System.out.println("Could not create list of chairs");

				setScript("alert('Monitor "+newMonitor.getName()+" with "+String.valueOf(numberOfColumns*numberOfRows)+" chairs was NOT added. Please contact the administrator if the problem persists.');");

			}
		}else {
			System.out.println("Monitor was not added");

			setScript("alert('Monitor "+newMonitor.getName()+" with "+String.valueOf(numberOfColumns*numberOfRows)+" chairs was NOT added. Please contact the administrator if the problem persists.');");
		}
		
		
		return null;

	}*/
	
	public String addMonitor() {
		Monitor newMonitor = new Monitor();
		List<Chair> newChairs = new ArrayList<Chair>();
		boolean unfinished = false;
		int index = 1;
		newMonitor.setName(monitor.getName());
		newMonitor.setDescription(monitor.getDescription());
		newMonitor.setRoom(RoomDao.getRoomById(roomId));
		if(MonitorDao.addMonitor(newMonitor)) {
			System.out.println("Monitor was added");		
			for(int i = 0;i < numberOfRows;i++) {
				for(int j= 0 ;j<numberOfColumns;j++) {
					if(ChairDao.addChair(new Chair(index,newMonitor,"{'Row' :"+String.valueOf(i)+", 'Coloumn' :"+String.valueOf(j)+"}"))) {
						
							System.out.println("Chairs were added");
							setScript("alert('Monitor "+newMonitor.getName()+" with "+String.valueOf(numberOfColumns*numberOfRows)+" chairs was added succesfully.');");

						}
						else {
							System.out.println("Chairs not added added");
							setScript("alert('Monitor "+newMonitor.getName()+" with "+String.valueOf(numberOfColumns*numberOfRows)+" chairs was NOT added. Please contact the administrator if the problem persists.');");
							
						}

				}
			}

		}else {
			System.out.println("Monitor was not added");

			setScript("alert('Monitor "+newMonitor.getName()+" with "+String.valueOf(numberOfColumns*numberOfRows)+" chairs was NOT added. Please contact the administrator if the problem persists.');");
		}
		
		
		return null;

	}
	
	public int getNumberOfChairs(int monitorId) {
			return ChairDao.getChairsOfMonitor(monitorId);
	}
	
	
	public void deleteMonitor() {}
	
	public void updateMonitor() {}
}
