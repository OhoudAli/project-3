package com.example.project3.Controller;


import com.example.project3.Model.Account;
import com.example.project3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {


    private final AccountService accountService;

    @PostMapping("/{customerId}")
    public ResponseEntity createAccount(@PathVariable Integer customerId, @RequestBody @Valid Account account) {
        accountService.createNewAccount(customerId, account);
        return ResponseEntity.ok("Account created successfully");
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity getCustomerAccounts(@PathVariable Integer customerId) {
        return ResponseEntity.ok(accountService.getMyAccount(customerId));
    }

    @GetMapping("/{customerId}/{accountId}")
    public ResponseEntity getAccount(@PathVariable Integer customerId, @PathVariable Integer accountId) {
        return ResponseEntity.ok(accountService.getOneAccount(customerId, accountId));
    }

    @DeleteMapping("/{customerId}/{accountId}")
    public ResponseEntity deleteAccount(@PathVariable Integer customerId, @PathVariable Integer accountId) {
        accountService.deleteAccount(customerId, accountId);
        return ResponseEntity.ok("Account deleted successfully");
    }

    @PostMapping("/{customerId}/{accountId}/withdraw")
    public ResponseEntity withdraw(@PathVariable Integer customerId, @PathVariable Integer accountId, @RequestParam Integer amount) {
        accountService.withdrawing(customerId, accountId, amount);
        return ResponseEntity.ok("Withdrawal successful");
    }

    @PostMapping("/deposit/{customerId}/{accountId}")
    public ResponseEntity deposit(@PathVariable Integer customerId, @PathVariable Integer accountId, @RequestParam Integer amount) {
        accountService.depositing(customerId, accountId, amount);
        return ResponseEntity.ok("Deposit successful");
    }

    @PostMapping("/transfer/{customerId}/{mainAccountId}/{secondAccountId}")
    public ResponseEntity transfer(@PathVariable Integer customerId, @PathVariable Integer mainAccountId, @PathVariable Integer secondAccountId, @RequestParam Integer amount) {
        accountService.transfer(customerId, mainAccountId, secondAccountId, amount);
        return ResponseEntity.ok("Transfer successful");
    }

}
