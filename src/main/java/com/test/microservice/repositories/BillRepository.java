package com.test.microservice.repositories;

import com.test.microservice.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
