package com.assignment.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.assignment.operations.DBOperationImpl;

/**
 * To upload Image
 * Servlet implementation class MainUtilityServlet
 */

public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBOperationImpl uploadImage;
       
    
    public ImageUploadServlet() {
        super();
    }
    
    public void init() {
    	uploadImage = new DBOperationImpl();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part part = request.getPart("image");
		HttpSession session=request.getSession(); 
		// Get userName from session object
		String userName = (String) session.getAttribute("userName");
		String message;
		
		boolean uploadResult = uploadImage.addImage(part, userName);
		
		// Getting Upload Status
		if (uploadResult) {
			message = uploadImage.getValidationMessage();
			request.setAttribute("UploadResult", message);
        	request.getRequestDispatcher("/MainUtility.jsp").forward(request, response);
        	System.out.println(message);

        } else {
			message = uploadImage.getValidationMessage();
        	request.setAttribute("UploadResult", message);
        	request.getRequestDispatcher("/MainUtility.jsp").forward(request, response);
        	System.out.println(message);
        }
			
	}
	
}
