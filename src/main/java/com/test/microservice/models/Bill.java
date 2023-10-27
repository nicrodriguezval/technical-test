package com.test.microservice.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "bills")
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

    public Bill() {
    }

    public Bill(Long id, Double totalAmount, String desc) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.desc = desc;
    }

    public Bill(Double totalAmount, String desc) {
        this.totalAmount = totalAmount;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", desc='" + desc + '\'' +
                ", user=" + user +
                '}';
    }
}
