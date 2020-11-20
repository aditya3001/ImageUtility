package com.assignment.operations;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.assignment.hibernateutil.HibernateUtil;

public class DeleteFromDB {

	private int rowsAffected;
	
	// Method to delete image from database
	public boolean delete (int imageId) {
		
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			
			rowsAffected = session.createQuery("DELETE FROM ImageInfo I WHERE I.ImageId = :imageId").setParameter("imageId", imageId).executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
            transaction.commit();
		 } catch (Exception e) {
			 System.out.println("Error while deleting");
			 if (transaction != null) {
	                transaction.rollback();
	            }
			 return false;
	     }
		return true;
	}
	
}
