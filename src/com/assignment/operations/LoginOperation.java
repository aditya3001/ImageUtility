package com.assignment.operations;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.assignment.hibernateutil.HibernateUtil;
import com.assignment.jpa.User;

public class LoginOperation {

    public boolean validate(String userName, String password) {

        Transaction transaction = null;
        User user = null;
        boolean validateResult;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // Extracting User instance
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName)
                .uniqueResult();

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