package com.al.akademia.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.al.akademia.dao.MonitorDao;
import com.al.akademia.entitete.Monitor;

@ManagedBean(name = "monitorBean")
@SessionScoped
public class MonitorBean {
	private List<Monitor> monitors;
	private Monitor monitor;
	
	public MonitorBean() {

		monitor = new Monitor();
		monitors = MonitorDao.getAllMonitors();
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
	

	
}
