package com.assignment.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment.operations.DeleteFromDB;
import com.assignment.operations.FetchFromDB;

/**
 * Contains method to delete image
 * Servlet implementation class DisplayTableServlet
 */
public class DeleteControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FetchFromDB fb;
	DeleteFromDB db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteControllerServlet() {
        super();
    }
    
    public void init() {
    	fb = new FetchFromDB();
    	db = new DeleteFromDB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int imageId = Integer.parseInt(request.getParameter("id"));
		boolean isDeleted;
		
		isDeleted = db.delete(imageId);
		
		// Get status of Delete process
		if (isDeleted) {
			System.out.println("Successfully Deleted!");
			response.sendRedirect("MainUtility.jsp");
		} else {
			System.out.println("Deletion Failed!");
		}
		
	}	

}
