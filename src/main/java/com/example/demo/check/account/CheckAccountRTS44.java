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
public class CheckAccountRTS44 extends CheckAccount {

    private static final String traderCode = "RTS";
    private static final String accountType = "44";
    private static final String url = "https://www.rts-tender.ru/";

    @Override
    public void check() {
        WebDriverRunner.setWebDriver(WEB_DRIVER_THREAD_LOCAL.get());
        open(url);

        log.trace("{} login...", url);
        $(By.xpath("//*[@id='login']")).click();
        $(By.xpath("//*[@id='44-fz-info']")).hover();
        $(By.xpath("//*[@id='44-fz-ru-btn-supplier']")).click();
        $(By.xpath("//*[@id='submitSignEsia']")).click();
        checkESIALogin(traderCode, accountType);
        log.trace("Вход через ЕСИА");

        loginWait(By.xpath("//label[contains(text(), 'Войти как физическое лицо')]")).click();
        log.trace("select first certificate");
        $(".certificate-list tr:nth-child(2)").click();
        $(By.xpath("//input[@value='Войти']")).click();
        log.trace("logout must be");
        $(By.cssSelector(".header__authorization .header__authorization_enter_text"));
        loginWait(By.xpath("//a[contains(text(),'Выход')]")).shouldBe(Condition.appear);
        closeESIALoginForm();
        log.info("check RTS44 is DONE");
    }
}
