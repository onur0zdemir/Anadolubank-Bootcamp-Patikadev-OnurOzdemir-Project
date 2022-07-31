package com.onurozdemir.javabootcamp.project.repository;

import com.onurozdemir.javabootcamp.project.model.Payment;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

import java.util.List;

public class PaymentsRepository {
    private SessionFactory sessionFactory;

    public PaymentsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Payment payment) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(payment);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Payment> getAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Payment> payments = null;

        try {
            tx = session.beginTransaction();
            SelectionQuery<Payment> selectionQuery = session.createSelectionQuery("FROM payment", Payment.class);
            payments = selectionQuery.getResultList();//https://docs.jboss.org/hibernate/orm/6.1/javadocs/org/hibernate/Session.html
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return payments;
    }
}
