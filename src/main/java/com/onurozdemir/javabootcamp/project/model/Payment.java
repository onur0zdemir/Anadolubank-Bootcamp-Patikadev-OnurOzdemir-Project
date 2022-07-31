package com.onurozdemir.javabootcamp.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "amount", nullable = false)
    private long amount;
    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;
    @ManyToOne
    @JoinColumn(name = "fk_customer_policy", nullable = false)
    private CustomerPolicy customerPolicy;
}
