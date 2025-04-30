package com.egt.core;

import com.egt.configuration.BrowserConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.stereotype.Component;

@Component
public class WebDriverFactory {

    private final BrowserConfig browserConfig;

    public WebDriverFactory(BrowserConfig browserConfig) {
        this.browserConfig = browserConfig;
    }

    public WebDriver createDriver() {
        if ("chrome".equalsIgnoreCase(browserConfig.getType())) {
            ChromeOptions options = getChromeOptions();
            return new ChromeDriver(options);

        } else if ("firefox".equalsIgnoreCase(browserConfig.getType())) {
            FirefoxOptions options = getFirefoxOptions();
            return new FirefoxDriver(options);

        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserConfig.getType());
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (browserConfig.isHeadless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--blink-settings=imagesEnabled=false");
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (browserConfig.isHeadless()) {
            options.addArguments("-headless");
        }
        options.addPreference("dom.disable_open_during_load", false);
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("dom.push.enabled", false);
        options.addPreference("permissions.default.image", 2);
        return options;
    }
}
