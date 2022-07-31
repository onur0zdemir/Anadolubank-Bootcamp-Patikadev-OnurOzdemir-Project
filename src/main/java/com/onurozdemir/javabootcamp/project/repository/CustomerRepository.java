package com.onurozdemir.javabootcamp.project.repository;

import com.onurozdemir.javabootcamp.project.model.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

import java.util.List;

public class CustomerRepository {
    private SessionFactory sessionFactory;

    public CustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(customer);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Customer> getAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Customer> customers = null;

        try {
            tx = session.beginTransaction();
            SelectionQuery<Customer> selectionQuery = session.createSelectionQuery("FROM customer", Customer.class);
            customers = selectionQuery.getResultList(); //https://docs.jboss.org/hibernate/orm/6.1/javadocs/org/hibernate/Session.html
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customers;
    }
}

