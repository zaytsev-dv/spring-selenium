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

@Slf4j
@Component
public class CheckAccountEIS extends CheckAccount {

    private static final String traderCode = "EIS";
    private static final String accountType = "";
    private static final String url = "https://zakupki.gov.ru/";

    //TODO: enable
    @Override
    public void check() {
//        WebDriverRunner.setWebDriver(WEB_DRIVER_THREAD_LOCAL.get());
//        open(url);
//        log.trace("{} login...", url);
//        $(".account-block").hover();
//        $(".account__var[data-toggle=modal-account-private]").shouldBe(Condition.visible);
//        $(".account__var[data-toggle=modal-account-private]").click();
//        $("input[type=submit").click();
//        checkESIALogin(traderCode, accountType);
//        loginWait(By.cssSelector(".login-tab-participant")).click();
//        log.trace(".personal-area-button must to be");
//        $(".personal-area-button").waitUntil(Condition.appear, 20000);
//        closeESIALoginForm();
//        log.info("check EIS is DONE");
    }
}
