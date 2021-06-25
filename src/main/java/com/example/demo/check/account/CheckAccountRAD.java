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
public class CheckAccountRAD extends CheckAccount {

    private static final String traderCode = "RAD";
    private static final String accountType = "";
    private static final String url = "https://gz.lot-online.ru/";

    @Override
    public void check() {
        WebDriverRunner.setWebDriver(WEB_DRIVER_THREAD_LOCAL.get());
        open(url);
        log.trace("{} login...", url);
        $(By.xpath("//button[@id='loginPopupLink']")).click();
        log.trace("Вход через ЕСИА");
        $(By.xpath("//*[@id=\"loginEsiaLink\"]")).shouldBe(Condition.appear);
        $(By.xpath("//*[@id=\"loginEsiaLink\"]")).click();
        checkESIALogin(traderCode, accountType);
        loginWait(By.xpath("//button[@data-value='login']")).click();
        log.trace("logout must be");
        $(By.xpath("//a[@id='logoutLink']")).shouldBe(Condition.appear);
        closeESIALoginForm();
        log.info("check RAD is DONE");
    }
}
