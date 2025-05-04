package com.example.project3.Controller;


import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

private final EmployeeService employeeService;

@PostMapping("/register")
    public ResponseEntity register( @Valid @RequestBody EmployeeDTO employeeDTO) {
         employeeService.register(employeeDTO);

         return ResponseEntity.status(200).body(new ApiResponse("Employee register successfully"));
    }


    @GetMapping("/get-employees")
    public ResponseEntity getAllEmployees(@AuthenticationPrincipal User employee) {
        return ResponseEntity.ok(employeeService.getAllEmployees(employee.getId()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal User employee) {
        employeeService.deleteEmployee(employee.getId());
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PutMapping("/accounts-activate/{accountId}")
    public ResponseEntity activateAccount(@AuthenticationPrincipal User employee, @PathVariable Integer accountId) {
        employeeService.activateAccount(employee.getId(), accountId);
        return ResponseEntity.ok("Account activated successfully");
    }

    @PutMapping("/block/{accountId}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal User employee, @PathVariable Integer accountId) {
        employeeService.blockAccount(employee.getId(), accountId);
        return ResponseEntity.ok("Account blocked successfully");
    }

}
