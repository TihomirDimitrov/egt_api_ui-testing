package com.egt.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class that maps all URLs used for navigation in the application.
 * <p>
 * It reads properties prefixed with 'app.egt.navigation' from application.yml and exposes them in a structured way.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.egt.navigation")
public class UrlConfig {
    private String automationPracticeForm;
    private String homePage;
}
