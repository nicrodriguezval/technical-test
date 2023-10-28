package com.test.microservice.services;

import com.test.microservice.dtos.CreateBillDto;
import com.test.microservice.dtos.UpdateBillDto;
import com.test.microservice.models.Bill;
import com.test.microservice.models.User;
import com.test.microservice.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final UserService userService;

    @Autowired
    public BillService(BillRepository billRepository, UserService userService) {
        this.billRepository = billRepository;
        this.userService = userService;
    }

    public Bill createBill(CreateBillDto billDto) {
        Optional<User> optionalUser = userService.getUser(billDto.getIdUser());

        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException("User with id " + billDto.getIdUser() + " not found");
        }

        Bill bill = new Bill();
        bill.setTotalAmount(billDto.getTotalAmount());
        bill.setDesc(billDto.getDesc());
        bill.setUser(optionalUser.get());

        return billRepository.save(bill);
    }

    public List<Bill> getBills() {
        return billRepository.findAll();
    }

    public Optional<Bill> getBill(Long id) {
        return billRepository.findById(id);
    }

    public Optional<Bill> updateBill(Long id, UpdateBillDto billDto) {
        return billRepository.findById(id).map(bill -> {
            bill.setTotalAmount(billDto.getTotalAmount());
            bill.setDesc(billDto.getDesc());
            return billRepository.save(bill);
        });
    }

    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }
}
