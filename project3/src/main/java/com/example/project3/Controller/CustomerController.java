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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
