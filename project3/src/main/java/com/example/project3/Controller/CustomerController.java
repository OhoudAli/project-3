package com.example.project3.Controller;


import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.DTO.UserDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {


    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDTO userDTO, @RequestBody @Valid CustomerDTO customerDTO){
        customerService.register(userDTO, customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Customer register successfully"));

    }


    @GetMapping("/{employeeId}")
    public ResponseEntity getAllCustomers(@PathVariable Integer employeeId) {
        return ResponseEntity.ok(customerService.getAllCustomers(employeeId));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity updateCustomer(@PathVariable Integer customerId, @RequestBody @Valid CustomerDTO customerDTO) {
        customerService.updateCustomer(customerId, customerDTO);
        return ResponseEntity.status(200).body("Customer updated successfully");
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
