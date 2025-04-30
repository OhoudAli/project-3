package com.example.project3.DTO;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    @NotNull
    private String position;

    @NotNull
    @Min(0)
    @Positive
    private double salary;


}
