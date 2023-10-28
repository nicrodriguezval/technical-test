package com.test.microservice.controllers;

import com.test.microservice.dtos.CreateBillDto;
import com.test.microservice.dtos.UpdateBillDto;
import com.test.microservice.models.Bill;
import com.test.microservice.services.BillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/bills")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody CreateBillDto billDto){
        Bill createdBill = billService.createBill(billDto);

        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Bill>> getBills() {
       List<Bill> bills = billService.getBills();

       return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<Bill> getBill(@PathVariable("billId") Long id) {
        Optional<Bill> foundBill = billService.getBill(id);

        if (foundBill.isEmpty()) {
            throw new NoSuchElementException("Bill with id " + id + " not found");
        }

        return new ResponseEntity<>(foundBill.get(), HttpStatus.OK);
    }

    @PutMapping("/{billId}")
    public ResponseEntity<Bill> updateBill(@PathVariable("billId") Long id, @RequestBody @Valid UpdateBillDto billDto) {
        Optional<Bill> updatedBill = billService.updateBill(id, billDto);

        if (updatedBill.isEmpty()) {
            throw new NoSuchElementException("Bill with id " + id + " not found");
        }

        return new ResponseEntity<>(updatedBill.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{billId}")
    public ResponseEntity<Bill> deleteBill(@PathVariable("billId") Long id) {
        Optional<Bill> foundBill = billService.getBill(id);

        if (foundBill.isEmpty()) {
            throw new NoSuchElementException("Bill with id " + id + " not found");
        }

        billService.deleteBill(id);

        return new ResponseEntity<>(foundBill.get(), HttpStatus.OK);
    }
}
