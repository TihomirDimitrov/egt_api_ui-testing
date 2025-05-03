package com.egt.factory;

import com.egt.configuration.BrowserConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.stereotype.Component;

import static com.egt.core.enums.BrowserType.CHROME;
import static com.egt.core.enums.BrowserType.FIREFOX;

/**
 * Factory class responsible for creating WebDriver instances based on configuration.
 * <p>
 * This class reads browser type and headless mode settings from {@link BrowserConfig}
 * and provides a fully configured WebDriver for either Chrome or Firefox.
 * </p>
 * <p>
 * The created WebDriver includes several optimized browser options such as disabling
 * popups, extensions, notifications, and images, and optionally running in headless mode.
 * </p>
 */
@Component
public class WebDriverFactory {

    private final BrowserConfig browserConfig;

    /**
     * Constructs a WebDriverFactory with the given browser configuration.
     *
     * @param browserConfig the configuration specifying browser type and options
     */
    public WebDriverFactory(BrowserConfig browserConfig) {
        this.browserConfig = browserConfig;
    }

    /**
     * Creates and returns a WebDriver instance based on the configured browser type.
     *
     * @return a new WebDriver instance (ChromeDriver or FirefoxDriver)
     * @throws IllegalArgumentException if the configured browser type is unsupported
     */
    public WebDriver createDriver() {
        if (CHROME.name().equalsIgnoreCase(browserConfig.getType())) {
            ChromeOptions options = getChromeOptions();
            return new ChromeDriver(options);

        } else if (FIREFOX.name().equalsIgnoreCase(browserConfig.getType())) {
            FirefoxOptions options = getFirefoxOptions();
            return new FirefoxDriver(options);

        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserConfig.getType());
        }
    }

    /**
     * Configures ChromeOptions with headless mode and additional browser optimizations.
     *
     * @return a configured ChromeOptions instance
     */
    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (browserConfig.isHeadless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments(
                "--disable-popup-blocking",
                "--disable-extensions",
                "--disable-infobars",
                "--disable-dev-shm-usage",
                "--no-sandbox",
                "--remote-allow-origins=*",
                "--start-maximized",
                "--blink-settings=imagesEnabled=false"
        );
        return options;
    }

    /**
     * Configures FirefoxOptions with headless mode and additional preferences to disable distractions.
     *
     * @return a configured FirefoxOptions instance
     */
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
