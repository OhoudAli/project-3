package com.example.project3.Controller;


import com.example.project3.Api.ApiResponse;
import com.example.project3.Service.AuthService;
import com.example.project3.Service.CustomerService;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {



    private final AuthService authService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDTO userDTO) {
        authService.createUser(userDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Registered successful"));
    }




}
