package com.example.demo.check.account;

import com.codeborne.selenide.WebDriverRunner;
import com.example.demo.check.account.base.CheckAccount;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;


import static com.codeborne.selenide.Selenide.open;
import static com.example.demo.util.CustomWebDriverProvider.WEB_DRIVER_THREAD_LOCAL;

@Slf4j
@Component
public class CheckAccountTAT44 extends CheckAccount {

    private static final String traderCode = "TAT";
    private static final String accountType = "44";
    private static final String url = "http://www.zakazrf.ru/";

    @Override
    public void check() {
        WebDriverRunner.setWebDriver(WEB_DRIVER_THREAD_LOCAL.get());
        open(url);
        log.trace("{} login...", url);
        open("http://webppo.zakazrf.ru/Logon/ParticipantsEIS");
        checkESIALogin(traderCode, accountType);
        clickWaitClick(By.cssSelector("#bt_CertLogon"), By.cssSelector(".select-certificate-button"));
        log.trace("user and logout must be");
        loginWait(By.cssSelector(".user")).exists();
        loginWait(By.cssSelector(".close-link")).exists();
        loginWait(By.cssSelector(".close-link")).click();
        closeESIALoginForm();
        log.info("check TAT44 is DONE");
    }
}
