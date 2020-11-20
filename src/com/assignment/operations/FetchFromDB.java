package com.assignment.operations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.assignment.hibernateutil.HibernateUtil;
import com.assignment.jpa.ImageInfo;

public class FetchFromDB {
	
	private List<ImageInfo> imageInfoList = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public List<ImageInfo> fetch (String userName) {
		System.out.println(userName);
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
            // get a user object
            imageInfoList = session.createQuery("FROM ImageInfo U WHERE U.UserName = :userName").setParameter("userName", userName).getResultList();
            System.out.println(imageInfoList.size());
            transaction.commit();
		 } catch (Exception e) {
			 if (transaction != null) {
	                transaction.rollback();
	            }
			 System.out.println("Failed while fetching");
	     }
		return imageInfoList;
	}

}
