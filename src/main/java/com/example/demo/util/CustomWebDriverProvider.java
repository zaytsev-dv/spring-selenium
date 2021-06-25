package com.example.demo.util;

import com.example.demo.config.SeleniumConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
@Component
public class CustomWebDriverProvider {

    public static ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL;
    private final SeleniumConfig seleniumConfig;

    public CustomWebDriverProvider(SeleniumConfig seleniumConfig) {
        this.seleniumConfig = seleniumConfig;
    }

    @PostConstruct
    public void onStart() {
        log.debug("CustomWebDriverProvider init: driver {}, ", seleniumConfig.getWebdriver());
        setSystemProperty("webdriver.chrome.driver", seleniumConfig.getWebdriver());
        setSystemProperty("selenide.browser", CustomWebDriverProvider.class.getCanonicalName());
        createDriver();
    }

    private void addTrustedCitesToCryptoProPlugin() {
        String trustedSitesUrl = new File("src/main/resources/config/trusted-sites.html").getAbsolutePath();
        String configTrustedSitesJs = "document.location.replace('" + trustedSitesUrl + "')";
        executeJavaScript(configTrustedSitesJs);
    }

    private void setSystemProperty(String key, String value) {
        System.setProperty(key, value);
        log.debug("System.setProperty({}, {})", key, value);
    }

    public void createDriver() {
        log.info("createDriver");
        ChromeOptions options = new ChromeOptions();

        //set gost
        options.setBinary(new File(seleniumConfig.getBrowser().getGost()));

        options.setAcceptInsecureCerts(true);
        options.setHeadless(false);

        options.addArguments("--disable-setuid-sandbox");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
        options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
        options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
        options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
        options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
        options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
        options.addArguments("--disable-notifications");
        log.debug("options: {}", options.asMap());

        options.addExtensions(new File(seleniumConfig.getExtensions().getCryptoPro()));
        options.addExtensions(new File(seleniumConfig.getExtensions().getGosUslugi()));

        ChromeDriver chromeDriver = new ChromeDriver(options);
        CustomWebDriverProvider.WEB_DRIVER_THREAD_LOCAL = ThreadLocal.withInitial(() -> chromeDriver);
//        WebDriverRunner.setWebDriver(WEB_DRIVER_THREAD_LOCAL.get());
//        open("https://www.google.ru/");
//        addTrustedCitesToCryptoProPlugin();
    }
}
