package com.assignment.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment.operations.DBOperationImpl;
import com.assignment.operations.DBOperationInterface;

/**
 * Contains method to delete image
 * Servlet implementation class DisplayTableServlet
 */
public class ActionControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBOperationInterface db;
	DBOperationInterface ub;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionControllerServlet() {
        super();
    }
    
    public void init() {
    	db = new DBOperationImpl();
    	ub = new DBOperationImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int imageId = Integer.parseInt(request.getParameter("id"));
		boolean isDeleted;
		
		isDeleted = db.deleteImage(imageId);
		
		// Get status of Delete process
		if (isDeleted) {
			System.out.println("Successfully Deleted!");
			response.sendRedirect("MainUtility.jsp");
		} else {
			System.out.println("Deletion Failed!");
		}
		
	}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int imageId = Integer.parseInt(request.getParameter("imageId"));
		String newName = request.getParameter("newName");
		boolean isUpdated = ub.editImageInfo(imageId, newName);
		
		// Getting Update Status
		if(isUpdated) {
			System.out.println("Updation Successful");
			response.sendRedirect("MainUtility.jsp");
		} else {
			System.out.println("Updation Failed");
		}
	}

}
