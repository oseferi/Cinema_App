package com.al.akademia.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.al.akademia.beans.LoginBean;
import com.al.akademia.beans.UserBean;


@WebFilter(filterName = "/UserFilter", urlPatterns = { "/*" })
public class AuthenticationFilter implements Filter {
	public static final String LOGIN_PAGE = "/login.xhtml";
	
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse  httpServletResponse = (HttpServletResponse) servletResponse;
		HttpSession session  = ((HttpServletRequest) servletRequest).getSession(false);
		
		
		UserBean userBean = (session != null) ? (UserBean) session.getAttribute("userBean") : null;
		//filterChain.doFilter(servletRequest, servletResponse);
		
		if(httpServletRequest.getRequestURI().contains("resources")) {
			filterChain.doFilter(servletRequest, servletResponse);
		}
		else if(httpServletRequest.getRequestURI().contains("javax.faces.resource")) {
			filterChain.doFilter(servletRequest, servletResponse);
		}
		else if(httpServletRequest.getRequestURI().contains("login")) {
			if(userBean!=null ) {
				if(userBean.getUser()!=null) {
					if(userBean.getUser().getUsername()!=null) {
						if(userBean.getUser().getRole().getId()==1) {
							httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/admin/index.xhtml");
						}
						else if(userBean.getUser().getRole().getId()==2) {
							httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user/client_home.xhtml");
						}
					}
					else{
						System.out.println("user is not logged in1");
						//httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + LOGIN_PAGE);
						filterChain.doFilter(servletRequest, servletResponse);
					}///
				}else{
					System.out.println("user is not logged in1");
					//httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + LOGIN_PAGE);
					filterChain.doFilter(servletRequest, servletResponse);
				}///
			}
			else{
					System.out.println("user is null1");
					//httpServletResponse.sendRedirect( httpServletRequest.getContextPath() + LOGIN_PAGE);
					filterChain.doFilter(servletRequest, servletResponse);
				}
		}
		
		else {
			if(userBean!=null ) {
				if(userBean.getUser()!=null) {
					if(userBean.getUser().getUsername()!=null) {
						System.out.println("user is logged in");
						if(userBean.getUser().getRole().getId()==1) {//eshte admin
							if(httpServletRequest.getRequestURI().contains("admin")) {
								filterChain.doFilter(servletRequest, servletResponse);
							}
							else {
								System.out.println("user is not authorized to access this resource");
								httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/admin/index.xhtml");
							}
						
						}
						
						
						else if(userBean.getUser().getRole().getId()==2) {//eshte klient
							if(httpServletRequest.getRequestURI().contains("user")) {
								filterChain.doFilter(servletRequest, servletResponse);
							}
							else {
								System.out.println("user is not authorized to access this resource");
								httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user/index.xhtml");
							}
						}
						
						else {//unknown type
							System.out.println("Anauthorized access for user with unknown role");
							httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/restricted.xhtml");
						}
					}
					else{
						System.out.println("user is not logged in");
						httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + LOGIN_PAGE);
						//filterChain.doFilter(servletRequest, servletResponse);
					}
				}else{
					System.out.println("user is null/login bean exists");
					httpServletResponse.sendRedirect( httpServletRequest.getContextPath() + LOGIN_PAGE);
					//filterChain.doFilter(servletRequest, servletResponse);
				}
			}else {
				System.out.println("user is null");
				httpServletResponse.sendRedirect( httpServletRequest.getContextPath() + LOGIN_PAGE);
				//filterChain.doFilter(servletRequest, servletResponse);
			}
			
		}
	
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("UserFilter Initialized");
		
	}
	

}
