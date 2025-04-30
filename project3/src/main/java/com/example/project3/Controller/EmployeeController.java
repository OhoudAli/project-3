package com.example.project3.Controller;


import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.DTO.UserDTO;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

private final EmployeeService employeeService;

@PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserDTO userDTO, @Valid @RequestBody EmployeeDTO employeeDTO) {
         employeeService.register(userDTO, employeeDTO);

         return ResponseEntity.status(200).body(new ApiResponse("Employee register successfully"));
    }

}
