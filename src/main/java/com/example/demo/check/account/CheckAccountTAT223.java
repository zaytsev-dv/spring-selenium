package com.example.demo.check.account;

import com.codeborne.selenide.WebDriverRunner;
import com.example.demo.check.account.base.CheckAccount;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;


import static com.codeborne.selenide.Selenide.open;
import static com.example.demo.util.CustomWebDriverProvider.WEB_DRIVER_THREAD_LOCAL;

@Slf4j
@Component
public class CheckAccountTAT223 extends CheckAccount {

    private static final String traderCode = "TAT";
    private static final String accountType = "223";
    private static final String url = "http://223etp.zakazrf.ru/";

    @Override
    public void check() {
        WebDriverRunner.setWebDriver(WEB_DRIVER_THREAD_LOCAL.get());
        open(url);
        log.trace("{} login...", url);
        open("http://223etp.zakazrf.ru/Html/Details/id/59");
        loginWait(By.xpath("//body/div[@id='body']/div[3]/div[1]/form[1]/div[1]/div[2]/span[1]/span[1]/a[2]/img[1]")).click();
        loginWait(By.xpath("//span[contains(text(),'Вход участником через ЕИС')]")).click();
        checkESIALogin(traderCode, accountType);
        loginWait(By.cssSelector("#bt_CertLogon")).click();
        loginWait(By.cssSelector(".select-certificate-button")).click();
        log.trace("user and logout must be");
        loginWait(By.xpath("//body/div[@id='header_wrap']/div[@id='nav_top']/div[1]/ul[1]/li[2]/a[4]/img[1]")).exists();
        loginWait(By.xpath("//body/div[@id='header_wrap']/div[@id='nav_top']/div[1]/ul[1]/li[2]/a[4]/img[1]")).click();
        closeESIALoginForm();
        log.info("check TAT223 is DONE");
    }
}
