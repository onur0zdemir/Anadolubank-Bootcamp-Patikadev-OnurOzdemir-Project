package com.onurozdemir.javabootcamp.project.repository;


import com.onurozdemir.javabootcamp.project.model.CustomerPolicy;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

import java.util.List;

public class CustomerPolicyRepository {
    private SessionFactory sessionFactory;

    public CustomerPolicyRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(CustomerPolicy customerPolicy) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(customerPolicy);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<CustomerPolicy> getAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<CustomerPolicy> customerPolicies = null;

        try {
            tx = session.beginTransaction();
            SelectionQuery<CustomerPolicy> selectionQuery = session.createSelectionQuery("FROM customer_policy", CustomerPolicy.class);
            customerPolicies = selectionQuery.getResultList();//https://docs.jboss.org/hibernate/orm/6.1/javadocs/org/hibernate/Session.html
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customerPolicies;
    }
}
