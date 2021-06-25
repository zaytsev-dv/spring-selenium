package com.example.demo.check.account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.example.demo.check.account.base.CheckAccount;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.example.demo.util.CustomWebDriverProvider.WEB_DRIVER_THREAD_LOCAL;

@Slf4j
@Component
public class CheckAccountTEK extends CheckAccount {

    private static final String traderCode = "TEK";
    private static final String accountType = "";
    private static final String url = "https://44.tektorg.ru/";

    @Override
    public void check() {
        WebDriverRunner.setWebDriver(WEB_DRIVER_THREAD_LOCAL.get());
        open(url);
        log.trace("{} login...", url);
        log.trace("Вход через ЕСИА");
        SelenideElement login = $(By.xpath("//button[span[contains(text(),'Вход через')]]"));
        if (!login.getText().equals("ВХОД ЧЕРЕЗ ГОСУСЛУГИ")) {
            throw new RuntimeException("not expected text: " + login.getText());
        }
        login.click();
        checkESIALogin(traderCode, accountType);
        loginWait(By.xpath("//button[@class='x-btn-text' and contains(text(), 'Выход')]"));
        closeESIALoginForm();
        log.info("check TEK is DONE");
    }
}
