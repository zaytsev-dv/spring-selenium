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
public class CheckAccountGPB extends CheckAccount {

    private static final String traderCode = "GPB";
    private static final String accountType = "";
    private static final String url = "https://gos.etpgpb.ru/";

    @Override
    public void check() {
        WebDriverRunner.setWebDriver(WEB_DRIVER_THREAD_LOCAL.get());
        open(url);
        log.trace("{} login...", url);
        log.trace("Вход через ЕСИА");
        $(By.xpath("//*[@data-availability-check=\"login-button\"]")).click();
        $(By.xpath("//*[@data-availability-check=\"login-via-esia-button\"]")).click();
        checkESIALogin(traderCode, accountType);
        loginWait(By.cssSelector(".userAccountBtn")).click();
        log.info("logout must be");
        $(".logout").shouldBe(Condition.appear);
        log.info("check GPB is DONE");
        closeESIALoginForm();
    }
}
