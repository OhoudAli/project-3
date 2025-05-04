package com.example.project3.Repository;


import com.example.project3.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Integer> {

    Customer findCustomerById(Integer id);
    List<Customer> findAllByUserId(Integer userId);
}
