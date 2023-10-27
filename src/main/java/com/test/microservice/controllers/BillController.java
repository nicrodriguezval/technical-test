package com.test.microservice.controllers;

import com.test.microservice.models.Bill;
import com.test.microservice.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bills")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        System.out.println(bill);
        Bill createdBill = billService.createBill(bill);

        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }
}
