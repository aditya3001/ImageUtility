package com.assignment.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assignment.operations.LoginImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginImpl loginOp;           
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControllerServlet() {
        super();
    }
    
    public void init() {
    	loginOp = new LoginImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("UserLogin.jsp");    //Navigate to User Login Page
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		
		        try {
		        	String username = request.getParameter("username");
		            String password = request.getParameter("password");
		            HttpSession session = request.getSession();  
		            
		            // Validate User
		            if (loginOp.validate(username, password)) {
		            	try {
		            		session.setAttribute("userName",username); 
		            		// Navigate to Main Utility Page after Successful Log In
		    	            RequestDispatcher dispatcher = request.getRequestDispatcher("MainUtility.jsp");
		    	            dispatcher.forward(request, response);
		    	            System.out.println("Successfully Logged in");
		            	} catch (Exception e) {
		            		System.out.println("Error while navigating towards Main Utility Page");
		            	}
		            } else {
		        		// Navigate to Login Page with error message
		        		request.setAttribute("LoginResult","Invalid Username or Password");
		        		RequestDispatcher dispatcher = request.getRequestDispatcher("UserLogin.jsp");
		                dispatcher.forward(request, response);
		                System.out.println("Invalid Username or Password");
		                
		            }
		        } catch (Exception e) {
		            System.out.println(e.getMessage());
		        }
		    }
	
}
