package com.example.project3.Repository;

import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    Account findAccountByAccountNumber( String accountNumber);

    Account findAccountByCustomer_Id(Integer customerId);

    List<Account> findAccountByCustomer(Customer customer);
}
