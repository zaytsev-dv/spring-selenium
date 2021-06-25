package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "check")
@Data
public class CronConfig {
    private String webdriver;
    private Account account;
    private Cite cite;

    @Data
    public static class Account {
        private String cron;
    }

    @Data
    public static class Cite {
        private String cron;
    }
}
