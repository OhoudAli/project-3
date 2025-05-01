package com.example.project3.Service;


import com.example.project3.Api.ApiException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {


    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;



    public Account getOneAccount(Integer customerId, Integer accountId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        return accountRepository.findAccountByIdAndCustomer(accountId, customer);
    }
    public List<Account> getAllAccounts(Integer EmployeeId) {
        return accountRepository.findAll();
    }

    public List<Account> getMyAccount(Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        return accountRepository.findAccountByCustomer(customer);
    }




    public void createNewAccount(Integer customerId, Account account) {

        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer is not found");

        }

        account.setCustomer(customer);
        account.setIsActive(false);
        accountRepository.save(account);

    }


    public void deleteAccount(Integer customerId, Integer accountId) {

        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer is not found");
        }
        Account account1 = accountRepository.findAccountByIdAndCustomer(accountId, customer);


        if (account1 == null) {
            throw new ApiException("Account is not found");
        }


        accountRepository.delete(account1);

    }


    public void withdrawing(Integer customerId, Integer accountId, Integer amount) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer is not found");
        }
        Account account1 = accountRepository.findAccountByIdAndCustomer(accountId, customer);


        if (account1 == null) {
            throw new ApiException("Account is not found");
        }
        if (!account1.getIsActive()) {
            throw new ApiException("Account is blocked");
        }


        if (account1.getBalance() < amount) {
            throw new ApiException("Insufficient balance ");

        }

        if (amount <= 0) {
            throw new ApiException("Try positive number ");

        }

        account1.setBalance(account1.getBalance() - amount);
        accountRepository.save(account1);

    }


    public void depositing(Integer customerId, Integer accountId, Integer amount) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer is not found");
        }
        Account account1 = accountRepository.findAccountByIdAndCustomer(accountId, customer);


        if (account1 == null) {
            throw new ApiException("Account is not found");
        }
        if (!account1.getIsActive()) {
            throw new ApiException("Account is blocked");
        }


        if (amount <= 0) {
            throw new ApiException("Add a positive amount ");
        }
        account1.setBalance(account1.getBalance() + amount);
        accountRepository.save(account1);

    }


    public void transfer(Integer customerId, Integer mainAccountId, Integer secondAccountId, Integer amount) {

        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer is not found");
        }
        Account account1 = accountRepository.findAccountByIdAndCustomer(mainAccountId, customer);


        if (account1 == null) {
            throw new ApiException("Account is not found");
        }
        if (!account1.getIsActive()) {
            throw new ApiException("Account is blocked");

        }

        Account account2 = accountRepository.findAccountById(secondAccountId);

        if (account2 == null) {
            throw new ApiException("Account is not found");
        }

        if (!account2.getIsActive()) {
            throw new ApiException("the account you try to transfer to is blocked");

        }

        withdrawing(customerId, account1.getId(), amount);
        account2.setBalance(account2.getBalance() + amount);

        accountRepository.save(account1);
        accountRepository.save(account2);

    }
}
