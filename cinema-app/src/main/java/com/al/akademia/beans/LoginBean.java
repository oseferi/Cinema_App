package com.al.akademia.beans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.al.akademia.dao.UserDao;
import com.al.akademia.entitete.User;

@ManagedBean
@RequestScoped
public class LoginBean {

	private String userName;
	private String password;
	private String script;
	
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	private UserDao userDao = new UserDao();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	
	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String logIn() {

		User user = UserDao.existUser(userName, password);
		if (user != null) {

			userBean.setUser(user);
			int roli = user.getRole().getId();
			System.out.println(roli);
			if (roli == 1) {

				return "admin/index?faces-redirect=true";
			} else {
				return "user/client_home?faces-redirect=true";
			}
		} else {
			setScript("alert('Username or Password is Incorrect');");
			System.out.println("te dhena te pasakta");
		}

		return null;
	}

	public String logOut() throws IOException {

		userBean.logOut();
		System.out.println("loging out");
		reload();
		 return null;
	}
	
	public void reload() throws IOException {
		   ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		   ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		}
}
