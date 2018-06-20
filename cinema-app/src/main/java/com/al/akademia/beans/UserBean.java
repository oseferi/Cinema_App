package com.al.akademia.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.al.akademia.entitete.User;

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean {
	
	private static User user;
	private int role;
	
	
	
	public UserBean(){
	
		user = new User();
		System.out.println("UserBean is loaded");	
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public static void logOut() {
		// TODO Auto-generated method stub
		user=null;
	}
	
}