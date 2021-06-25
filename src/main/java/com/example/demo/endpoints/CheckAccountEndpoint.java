package com.example.demo.endpoints;

import com.example.demo.check.account.base.CheckAccount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckAccountEndpoint {
    private final List<CheckAccount> checkAccounts;

    public CheckAccountEndpoint(List<CheckAccount> checkAccounts) {
        this.checkAccounts = checkAccounts;
    }

    @GetMapping("/test")
    public void test() {
        checkAccounts.forEach(CheckAccount::check);
    }
}
