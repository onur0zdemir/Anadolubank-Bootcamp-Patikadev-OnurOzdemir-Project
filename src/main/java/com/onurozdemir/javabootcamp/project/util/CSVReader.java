package com.onurozdemir.javabootcamp.project.util;

import com.onurozdemir.javabootcamp.project.model.Customer;
import com.onurozdemir.javabootcamp.project.model.InsuranceAgency;
import com.onurozdemir.javabootcamp.project.model.Payment;
import com.onurozdemir.javabootcamp.project.model.Policy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        List<String[]> str = csvReader.readCSV("INSURANCE_AGENCY_MOCK_DATA.csv");
        List<InsuranceAgency> insuranceAgencies = csvReader.insuranceAgencies();
        for (InsuranceAgency insuranceAgency : insuranceAgencies) {
            System.out.println(insuranceAgency);
        }
    }

    private final String COMMA_DELIMETER = ",";

    public List<String[]> readCSV(String fileName) {
        List<String[]> lines = new ArrayList<>();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);


        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMETER);
                lines.add(values);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    public List<Customer> customers() {
        List<Customer> customers = new ArrayList<>();
        List<String[]> lines = readCSV("CUSTOMER_MOCK_DATA.csv");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (String[] line : lines) {
            try {
                Customer customer = new Customer(
                        Long.parseLong(line[0]),
                        line[1],
                        line[2],
                        line[3],
                        simpleDateFormat.parse(line[4])
                );
                customers.add(customer);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return customers;
    }

    public List<InsuranceAgency> insuranceAgencies() {
        List<InsuranceAgency> insuranceAgencies = new ArrayList<>();
        List<String[]> lines = readCSV("INSURANCE_AGENCY_MOCK_DATA.csv");
        for (String[] line : lines) {
            InsuranceAgency insuranceAgency = new InsuranceAgency(
                    Long.parseLong(line[0]),
                    line[1],
                    line[2]
            );
            insuranceAgencies.add(insuranceAgency);
        }
        return insuranceAgencies;
    }

    public List<Payment> payments() {
        List<Payment> payments = new ArrayList<>();
        List<String[]> lines = readCSV("PAYMENT_MOCK_DATA.csv");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (String[] line : lines) {
            try {
                Payment payment = new Payment(
                        Long.parseLong(line[0]),
                        Long.parseLong(line[1]),
                        simpleDateFormat.parse(line[2]),
                        null
                );
                payments.add(payment);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return payments;
    }

    public List<Policy> policies() {
        List<Policy> policies = new ArrayList<>();
        List<String[]> lines = readCSV("POLICY_MOCK_DATA.csv");
        for (String[] line : lines) {
            Policy policy = new Policy(
                    Long.parseLong(line[0]),
                    line[1],
                    null
            );
            policies.add(policy);
        }
        return policies;
    }
}