package com.assignment.operations;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.assignment.hibernateutil.HibernateUtil;

public class UpdateDB {
	
	public boolean Update (int imageId, String newName) {
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
            int status = session.createQuery("update ImageInfo set Name = :n where ImageId = :i")
            			.setParameter("n", newName)
            			.setParameter("i", imageId).executeUpdate();
            System.out.println(status);
            transaction.commit();
		 } catch (Exception e) {
			 if (transaction != null) {
	                transaction.rollback();
	            }
	            return false;
	     }
		return true;
	}

}
