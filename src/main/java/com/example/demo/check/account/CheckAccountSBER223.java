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
public class CheckAccountSBER223 extends CheckAccount {

    private static final String traderCode = "SBER";
    private static final String accountType = "223";
    private static final String url = "https://utp.sberbank-ast.ru/";

    @Override
    public void check() {
        WebDriverRunner.setWebDriver(WEB_DRIVER_THREAD_LOCAL.get());
        open(url);
        log.trace("{} login...", url);
        $(By.cssSelector("#enterSpan")).click();
        log.trace("Вход через ЕСИА");
        $(By.xpath("//a[contains(text(),'Вход через ЕСИА')]")).click();
        checkESIALogin(traderCode, accountType);
        loginWait(By.cssSelector("#btnEnter")).click();
        log.trace("logout must be");
        $(By.xpath("//*[@class='master_open_login']/a[text()='Выйти']")).shouldBe(Condition.exist);
        closeESIALoginForm();
        log.info("check SBER223 is DONE");
    }
}
