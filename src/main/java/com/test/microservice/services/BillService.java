package com.test.microservice.services;

import com.test.microservice.dtos.CreateBillDto;
import com.test.microservice.dtos.UpdateBillDto;
import com.test.microservice.models.Bill;
import com.test.microservice.models.User;
import com.test.microservice.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill createBill(CreateBillDto billDto) {
        User user = new User();
        user.setId(billDto.getUserId());

        Bill bill = new Bill();
        bill.setTotalAmount(billDto.getTotalAmount());
        bill.setDesc(billDto.getDesc());
        bill.setUser(user);

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
