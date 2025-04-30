package com.example.project3.Service;

import com.example.project3.DTO.CustomerDTO;
import com.example.project3.DTO.UserDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;


    public void register(UserDTO userDTO, CustomerDTO customerDTO){
        User user = new User();

        user.setRole("CUSTOMER");
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        String hashPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);


        Customer customer = new Customer();
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        customer.setUser(user);
        customerRepository.save(customer);
    }
}
