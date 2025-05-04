package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;


    public void register( CustomerDTO customerDTO){
        User user = new User();

        user.setRole("CUSTOMER");
        user.setUsername(customerDTO.getUsername());
        user.setName(customerDTO.getName());
        String hashPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);


        Customer customer = new Customer();
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        customer.setUser(user);
        customerRepository.save(customer);
    }


    public List<Customer> getAllCustomers(Integer userId) {
        return customerRepository.findAllByUserId(userId);
    }

    public Customer getCustomerById(Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("Customer not found");
        }
        return customer;
    }


    public void updateCustomer(Integer customerId,CustomerDTO customerDTO){

        Customer customer= customerRepository.findCustomerById(customerId);

        if (customer==null){
            throw new ApiException("customer is not found");
        }


        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        customerRepository.save(customer);


    }

    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if(customer == null){
              throw   new ApiException("Customer not found");
        }
        customerRepository.delete(customer);
        authRepository.delete(customer.getUser());
    }
}
