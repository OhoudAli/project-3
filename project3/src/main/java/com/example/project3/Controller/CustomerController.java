package com.example.project3.Controller;


import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {


    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CustomerDTO customerDTO){
        customerService.register(customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Customer register successfully"));

    }


    @GetMapping("/get-all-customers")
    public ResponseEntity getAllCustomers(@AuthenticationPrincipal User employee) {
        return ResponseEntity.ok(customerService.getAllCustomers(employee.getId()));
    }

    @PutMapping("/update")
    public ResponseEntity updateCustomer( @AuthenticationPrincipal User customer,@RequestBody @Valid CustomerDTO customerDTO) {
        customerService.updateCustomer( customer.getId(),customerDTO);
        return ResponseEntity.status(200).body("Customer updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User customer) {
        customerService.deleteCustomer(customer.getId());
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
