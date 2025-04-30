package com.example.project3.Service;


import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.DTO.UserDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public void register(UserDTO userDTO,EmployeeDTO employeeDTO){
        User user = new User();

        user.setRole("EMPLOYEE");
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        String hashPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);

        Employee employee =new Employee();
        employee.setSalary(employeeDTO.getSalary());
        employee.setPosition(employeeDTO.getPosition());

        employee.setUser(user);
        employeeRepository.save(employee);



    }
}
