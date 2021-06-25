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
public class CheckAccountETP223 extends CheckAccount {

    private static final String traderCode = "EETP";
    private static final String accountType = "223";

    @Override
    public void check() {
        WebDriverRunner.setWebDriver(WEB_DRIVER_THREAD_LOCAL.get());
        open("https://msp.roseltorg.ru/");
        log.trace("{} login...", "https://msp.roseltorg.ru/");
        log.trace("Вход через ЕСИА");
        $(By.xpath("//span[text()='ГОСУСЛУГИ (ЕРУЗ)']")).click();
        checkESIALogin(traderCode, accountType);
        log.trace("user info must be");
        loginWait(By.cssSelector(".first"), Condition.exist);
        $(".second").exists();
        $(".user-inn").exists();
        $(".third").exists();
        closeESIALoginForm();
        log.info("check EETP223 is DONE");
    }
}
