package com.test.microservice.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateBillDto {
    @NotNull(message = "totalAmount is required")
    @DecimalMin(value = "0.01", message = "Total amount must be greater than or equal to 0.01")
    private Double totalAmount;

    @NotBlank(message = "desc is required")
    @Size(max = 255, message = "Description must be less than or equal to 255 characters")
    private String desc;

    @NotNull(message = "userId is required")
    private Long userId;
}
