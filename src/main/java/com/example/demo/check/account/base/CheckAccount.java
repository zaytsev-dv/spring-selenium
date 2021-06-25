package com.example.demo.check.account.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Slf4j
public abstract class CheckAccount {

    public abstract void check();

    protected void checkESIALogin(String traderCode, String accountType) {
        AtomicInteger countIteration = new AtomicInteger(0);
        boolean displayed = isDisplayed("//body/div[2]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/a[1]", traderCode, accountType);
        if (displayed) {
            loginInESIA();
        } else {
            while (!displayed && countIteration.get() != 5) {
                countIteration.incrementAndGet();
                if (isDisplayed("//body/div[2]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/a[1]", traderCode, accountType)) {
                    loginInESIA();
                    displayed = true;
                    log.warn("end while because displayed == true");
                }
            }
        }
    }

    private boolean isDisplayed(String xPath, String traderCode, String accountType) {
        boolean displayed = false;
        try {
            displayed = new WebDriverWait(getWebDriver(), 20)
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)))
                    .isDisplayed();
        } catch (TimeoutException ex) {
            log.error("element by xPath: " + xPath + " " + "cannot find by Timeout");
            LocalDateTime localDateTime = LocalDateTime.now();
            StringBuilder stringBuilder = new StringBuilder("/").append(traderCode).append("/")
                    .append(DateTimeFormatter.ofPattern("yyyyMM").format(localDateTime)).append("/")
                    .append(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(localDateTime))
                    .append("_").append(accountType);

            com.codeborne.selenide.Selenide.screenshot(stringBuilder.toString());

        } catch (Exception ex) {
            log.error("unexpected error: ", ex);
        }
        return displayed;
    }

    private void loginInESIA() {
        log.info("login to ESIA");
        wait(By.xpath("//body/div[2]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/a[1]")).click();
        wait(By.xpath("//span[contains(text(),'Готово')]")).click();
        wait(By.xpath("//body/div[7]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]")).click();
        wait(By.xpath("//span[contains(text(),'Продолжить без пин-кода')]")).click();
    }

    protected SelenideElement wait(By expected) {
        return wait(expected, Condition.appear);
    }

    protected SelenideElement wait(By expected, Condition condition) {
        log.trace("wait until {}", expected);
        return $(expected).waitUntil(condition, 10000);
    }

    protected void closeESIALoginForm() {
        open("https://esia.gosuslugi.ru/");
        wait(By.xpath("//header/div[1]/div[2]/div[1]/a[2]")).click();
    }
}
