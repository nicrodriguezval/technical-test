package com.test.microservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Double totalAmount;

    // 👇 Here, the column had to be stored as "description" due the word "desc" is a reserved word in SQL
    @Column(name = "description")
    private String desc;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "id_user",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;
}
