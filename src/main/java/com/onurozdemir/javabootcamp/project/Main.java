package com.onurozdemir.javabootcamp.project;

import com.onurozdemir.javabootcamp.project.model.*;
import com.onurozdemir.javabootcamp.project.repository.*;
import com.onurozdemir.javabootcamp.project.util.CSVReader;
import com.onurozdemir.javabootcamp.project.util.DatabaseConnection;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        SessionFactory sessionFactory = DatabaseConnection.setup();

        InsuranceAgencyRepository insuranceAgencyRepository = new InsuranceAgencyRepository(sessionFactory);
        PolicyRepository policyRepository = new PolicyRepository(sessionFactory);
        CustomerRepository customerRepository = new CustomerRepository(sessionFactory);
        CustomerPolicyRepository customerPolicyRepository = new CustomerPolicyRepository(sessionFactory);
        PaymentsRepository paymentsRepository = new PaymentsRepository(sessionFactory);

        CSVReader csvReader = new CSVReader();
        List<InsuranceAgency> insuranceAgencies = csvReader.insuranceAgencies();
        List<Policy> policies = new ArrayList<>();
        for (InsuranceAgency insuranceAgency : insuranceAgencies) {
            insuranceAgencyRepository.save(insuranceAgency);
            List<Policy> policiesOfAgency = csvReader.policies();
            for (Policy policy : policiesOfAgency) {
                policy.setInsuranceAgency(insuranceAgency);
                policyRepository.save(policy);
            }
            policies.addAll(policiesOfAgency);
        }
        List<Customer> customers = csvReader.customers();
        List<CustomerPolicy> customerPolicies = new ArrayList<>();
        for (Customer customer : customers) {
            customerRepository.save(customer);
            int randomPolicyIndex = new Random().nextInt(policies.size() - 2);
            for (int i = 0; i < 3; i++, randomPolicyIndex++) {
                CustomerPolicy customerPolicy = new CustomerPolicy(0, customer, policies.get(randomPolicyIndex));
                customerPolicyRepository.save(customerPolicy);
                customerPolicies.add(customerPolicy);
            }
        }
        List<Payment> payments = csvReader.payments();
        int index = 0;
        for (Payment payment : payments) {
            payment.setCustomerPolicy(customerPolicies.get(index++ % customerPolicies.size()));
            paymentsRepository.save(payment);
        }
    }
}