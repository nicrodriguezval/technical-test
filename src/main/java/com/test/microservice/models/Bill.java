package com.test.microservice.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bills")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Bill {
    @Id
    @SequenceGenerator(
            name = "bill_sequence",
            sequenceName = "bill_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bill_sequence"
    )
    private Long id;
    private Double totalAmount;

    // 👇 Here, the column had to be stored as "description" due the word "desc" is a reserved word in SQL
    @Column(name = "description")
    private String desc;

    @ManyToOne
    @JoinColumn(
            name = "id_user", nullable = false,
            updatable = false
    )
    private User user;
}
