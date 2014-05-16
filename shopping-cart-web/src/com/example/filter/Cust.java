package com.example.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.DaoException;
import com.example.service.LoginService;
import com.example.service.LoginServiceImpl;

/**
 * Servlet Filter implementation class Cust
 */
public class Cust implements Filter {
	private LoginService loginService;
	
    /**
     * Default constructor. 
     */
    public Cust() {
        loginService = new LoginServiceImpl();
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hssr = (HttpServletRequest) request;
		HttpSession hs = hssr.getSession();
		String username = (String) hs.getAttribute("username");
		String userRole = null;
		if(username == null){
			HttpServletResponse hsr = (HttpServletResponse) response;
			hsr.sendRedirect("logout");
			return;
		}
		try {
			userRole = loginService.getUser(username).getRole();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(userRole != null && !userRole.equals("cust")){
			HttpServletResponse hsr = (HttpServletResponse) response;
			hsr.sendRedirect("logout");
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
