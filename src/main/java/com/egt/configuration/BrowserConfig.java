package com.egt.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class that connects browser settings from application.yml.
 *<p> Reads properties prefixed with 'browser' and provides information such as:
 * <ul>
 *     <li>which browser to use (chrome, firefox and etc)</li>
 *     <li>whether to run tests in headless mode</li>
 * </ul>
 * This class is used by WebDriverFactory to dynamically create the proper WebDriver.
 */

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "browser")
public class BrowserConfig {
    private String type;     // chrome, firefox, edge
    private boolean headless;
}
