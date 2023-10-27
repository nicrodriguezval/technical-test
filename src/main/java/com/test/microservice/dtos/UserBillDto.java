package com.test.microservice.dtos;

import com.test.microservice.models.Bill;
import com.test.microservice.models.User;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserBillDto {
    private User user;
    private List<Bill> bills;
}
