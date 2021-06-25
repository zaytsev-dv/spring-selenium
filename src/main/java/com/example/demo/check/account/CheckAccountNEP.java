package com.example.demo.check.account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.example.demo.check.account.base.CheckAccount;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.example.demo.util.CustomWebDriverProvider.WEB_DRIVER_THREAD_LOCAL;

@Slf4j
@Component
public class CheckAccountNEP extends CheckAccount {

    private static final String traderCode = "NEP";
    private static final String accountType = "";
    private static final String url = "https://www.etp-ets.ru/";

    @Override
    public void check() {
        WebDriverRunner.setWebDriver(WEB_DRIVER_THREAD_LOCAL.get());
        open(url);
        log.trace("{} login...", url);
        $(By.xpath("//button//span[text()='Войти']")).click();
        log.trace("NEP: Вход через ЕСИА");
        WebElement iframe = getWebDriver().findElement(By.xpath("/html/body/div[3]/div[4]/div/div/iframe"));
        getWebDriver().switchTo().frame(iframe);
        $(By.xpath("//a[@href='/api/esia/auth']")).shouldBe(Condition.appear);
        $(By.xpath("//a[text()='Войти через' and @href='/api/esia/auth']")).click();

        checkESIALogin(traderCode, accountType);

        log.trace("NEP: Ждем входа через ЕСИА");
        loginWait(By.cssSelector(".navbar-exit>.exit-dialog"));
        log.trace("NEP: logout must be");

        SelenideElement logout = $(".navbar-exit .exit-dialog");
        if (!logout.getText().equalsIgnoreCase("выход")) {
            throw new RuntimeException("not expected text: " + logout.getText());
        }
        closeESIALoginForm();
        log.info("check NEP is DONE");
    }
}
