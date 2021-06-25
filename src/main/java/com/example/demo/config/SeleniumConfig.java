package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "selenide")
@Data
public class SeleniumConfig {
    private String webdriver;
    private Extensions extensions;
    private Browser browser;

    @Data
    public static class Extensions {
        private String cryptoPro;
        private String gosUslugi;
    }

    @Data
    public static class Browser {
        private String gost;
    }
}
