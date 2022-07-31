package com.onurozdemir.javabootcamp.project.repository;

import com.onurozdemir.javabootcamp.project.model.InsuranceAgency;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

import java.util.List;

public class InsuranceAgencyRepository {
    private SessionFactory sessionFactory;

    public InsuranceAgencyRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(InsuranceAgency insuranceAgency) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(insuranceAgency);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<InsuranceAgency> getAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<InsuranceAgency> insuranceAgencies = null;

        try {
            tx = session.beginTransaction();
            SelectionQuery<InsuranceAgency> selectionQuery = session.createSelectionQuery("FROM insurance_agency", InsuranceAgency.class);
            insuranceAgencies = selectionQuery.getResultList();//https://docs.jboss.org/hibernate/orm/6.1/javadocs/org/hibernate/Session.html
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return insuranceAgencies;
    }
}
