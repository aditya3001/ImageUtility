package com.assignment.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment.operations.UpdateDB;

/**
 * Contains Method to edit the Name of Image
 * Servlet implementation class EditControllerServlet
 */
public class EditControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UpdateDB ub;
       

    public EditControllerServlet() {
        super();
    }
    
    public void init() {
    	ub = new UpdateDB();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int imageId = Integer.parseInt(request.getParameter("imageId"));
		String newName = request.getParameter("newName");
		boolean isUpdated = ub.Update(imageId, newName);
		
		// Getting Update Status
		if(isUpdated) {
			System.out.println("Updation Successful");
			response.sendRedirect("MainUtility.jsp");
		} else {
			System.out.println("Updation Failed");
		}
	}

}
