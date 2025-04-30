package com.egt.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.egt.navigation")
public class UrlConfig {
    private String automationPracticeForm;
    private String homePage;
}
