package com.assignment.operations;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.assignment.hibernateutil.HibernateConnection;
import com.assignment.jpa.User;

public class LoginImpl implements LoginInterface {

    @Override
	public boolean validate(String userName, String password) {

        Transaction transaction = null;
        boolean validateResult;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            User user = session.get(User.class, userName);
                    
            // Validating result
            if (user != null && user.getPassword().equals(password)) {
            	validateResult = true;
            } else {
            	validateResult = false;
            	System.out.println("Either UserName or Password is Invalid!");
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
        return validateResult;
    }

}