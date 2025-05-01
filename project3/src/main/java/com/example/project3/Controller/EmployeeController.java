package com.example.project3.Controller;


import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.DTO.UserDTO;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{employeeId}")
    public ResponseEntity getAllEmployees(@PathVariable Integer employeeId) {
        return ResponseEntity.ok(employeeService.getAllEmployees(employeeId));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PutMapping("/accounts-activate/{employeeId}/{accountId}")
    public ResponseEntity activateAccount(@PathVariable Integer employeeId, @PathVariable Integer accountId) {
        employeeService.activateAccount(employeeId, accountId);
        return ResponseEntity.ok("Account activated successfully");
    }

    @PutMapping("/accounts-block/{employeeId}/{accountId}")
    public ResponseEntity blockAccount(@PathVariable Integer employeeId, @PathVariable Integer accountId) {
        employeeService.blockAccount(employeeId, accountId);
        return ResponseEntity.ok("Account blocked successfully");
    }

}
