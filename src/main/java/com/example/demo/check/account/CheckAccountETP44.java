package com.example.demo.check.account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.example.demo.check.account.base.CheckAccount;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.example.demo.util.CustomWebDriverProvider.WEB_DRIVER_THREAD_LOCAL;

@Component
@Slf4j
public class CheckAccountETP44 extends CheckAccount {

    private static final String traderCode = "EETP";
    private static final String accountType = "44";
    private static final String url = "https://etp.roseltorg.ru/";

    @Override
    public void check() {
        WebDriverRunner.setWebDriver(WEB_DRIVER_THREAD_LOCAL.get());
        open(url);
        //todo: //add trusted sites after "open"
        log.trace("{} login...", url);
        $(By.xpath("//button[span[contains(text(),'Вход через')]]")).click();
        log.trace("Вход через ЕСИА");
        checkESIALogin(traderCode, accountType);
        wait(By.xpath("//button[text()='Выход']"), Condition.exist);
        closeESIALoginForm();
    }
}
