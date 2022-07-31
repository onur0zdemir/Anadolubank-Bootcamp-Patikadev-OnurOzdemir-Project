package com.onurozdemir.javabootcamp.project.repository;

import com.onurozdemir.javabootcamp.project.model.Policy;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

import java.util.List;

public class PolicyRepository {
    private SessionFactory sessionFactory;

    public PolicyRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Policy policy) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(policy);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Policy> getAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Policy> policies = null;

        try {
            tx = session.beginTransaction();
            SelectionQuery<Policy> selectionQuery = session.createSelectionQuery("FROM policy", Policy.class);
            policies = selectionQuery.getResultList();//https://docs.jboss.org/hibernate/orm/6.1/javadocs/org/hibernate/Session.html
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return policies;
    }
}
