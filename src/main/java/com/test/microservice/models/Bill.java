package com.test.microservice.models;

public class Bill {
    private Long id;
    private Double totalAmount;
    private String desc;

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
}
