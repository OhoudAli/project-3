package com.example.project3.Service;


import com.example.project3.Api.ApiException;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Account;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;
    private final AccountRepository accountRepository;

    public void register(EmployeeDTO employeeDTO){
        User user = new User();

        user.setRole("EMPLOYEE");
        user.setUsername(employeeDTO.getUsername());
        user.setName(employeeDTO.getName());
        user.setEmail(employeeDTO.getEmail());

        String hashPassword = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);

        Employee employee =new Employee();
        employee.setSalary(employeeDTO.getSalary());
        employee.setPosition(employeeDTO.getPosition());

        employee.setUser(user);
        employeeRepository.save(employee);



    }

    public List<Employee> getAllEmployees(Integer employeeId){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        Employee employee= employeeRepository.findEmployeeById(id);
                if(employee == null) {
                    new ApiException("Employee not found");
                }
            return employee;
    }



    public void deleteEmployee(Integer employeeId){

        Employee employee= employeeRepository.findEmployeeById(employeeId);

        if (employee==null){
            throw new ApiException("the employee is not found");
        }

        authRepository.delete(employee.getUser());


    }

    public void activateAccount(Integer employeeId,Integer accountId){

        Employee employee= employeeRepository.findEmployeeById(employeeId);

        if (employee==null){
            throw new ApiException("the employee is not found");
        }


        Account account = accountRepository.findAccountById(accountId);


        if (account==null){
            throw new ApiException("the account is not found ");

        }

        if (account.getIsActive()){
            throw new ApiException("the account is already active ");
        }

        account.setIsActive(true);
        accountRepository.save(account);


    }

    public void updateEmployee(Integer userId,Integer employeeId, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        if (employee==null){
            throw new ApiException("Employee not found");
        }
        if (employee.getUser().getId()!=userId){
            throw new ApiException("Unauthorized");
        }

        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());

        employeeRepository.save(employee);
    }

    public void blockAccount(Integer employeeId, Integer accountId){

        Employee employee= employeeRepository.findEmployeeById(employeeId);

        if (employee==null){
            throw new ApiException("Employee is not found");
        }


        Account account = accountRepository.findAccountById(accountId);

        if (account==null){
            throw new ApiException("Account is not found ");

        }

        if (!account.getIsActive()){
            throw new ApiException("Account is already blocked ");
        }


        account.setIsActive(false);
        accountRepository.save(account);

    }



}
