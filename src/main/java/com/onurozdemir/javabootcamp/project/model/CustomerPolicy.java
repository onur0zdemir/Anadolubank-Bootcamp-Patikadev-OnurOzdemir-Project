package com.onurozdemir.javabootcamp.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customer_policy")
public class CustomerPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @ManyToOne
    @JoinColumn(name = "fk_customer", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "fk_policy", nullable = false)
    private Policy policy;
}