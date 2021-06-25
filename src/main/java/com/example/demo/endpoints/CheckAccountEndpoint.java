package com.example.demo.endpoints;

import com.example.demo.check.account.base.CheckAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CheckAccountEndpoint {
    private final List<CheckAccount> checkAccounts;

    public CheckAccountEndpoint(List<CheckAccount> checkAccounts) {
        this.checkAccounts = checkAccounts;
    }

    @GetMapping("/test")
    public void test() {
        checkAccounts.forEach(ca-> {
            try {
                ca.check();
                log.info("All check ETP is DONE");
            } catch (Exception ex) {
                log.error("", ex);
                ca.closeESIALoginForm();
            }
        });
    }
}
