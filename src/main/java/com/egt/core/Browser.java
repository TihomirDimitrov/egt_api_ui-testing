package com.egt.core;

import com.egt.core.enums.WaitType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Browser {
    private static final Logger log = LoggerFactory.getLogger(Browser.class);
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> BROWSER_STATE = new ThreadLocal<>();
    private Actions actions = null;

    private final WebDriverFactory webDriverFactory;
    private final PageObjectFactory pageObjectFactory;
    private final WaitPresetRegistry waitPresetRegistry;

    public Browser(WebDriverFactory webDriverFactory, PageObjectFactory pageObjectFactory,
                   WaitPresetRegistry waitPresetRegistry) {
        this.webDriverFactory = webDriverFactory;
        this.pageObjectFactory = pageObjectFactory;
        this.waitPresetRegistry = waitPresetRegistry;
    }

    public WebDriver getDriver() {
        if (DRIVER.get() == null) {
            WebDriver driver = webDriverFactory.createDriver();
            DRIVER.set(driver);
            BROWSER_STATE.set(false);
            driver.manage().window().maximize();
        }
        return DRIVER.get();
    }

    public Browser navigateTo(String url) {
        this.getDriver().get(url);
        return this;
    }

    public <T extends Page> T navigateTo(String url, Class<T> pageType) {
        this.getDriver().navigate().to(url);
        return this.switchToPage(pageType);
    }

    public <T extends Page> T switchToPage(Class<T> pageType) {
        return pageObjectFactory.create(pageType, this);
    }

    public void close() {
        try {
            if (DRIVER.get() != null) {
                DRIVER.get().quit();
            }
        } catch (Exception e) {
            log.error("Error closing WebDriver: {}", e.getMessage());
        } finally {
            DRIVER.remove();
            BROWSER_STATE.remove();
        }
    }

    public Actions actions() {
        if (actions == null) {
            actions = new Actions(getDriver());
        }
        return actions;
    }

    public FluentWait<WebDriver> wait(WaitType waitType) {
        return waitPresetRegistry.getWait(waitType, getDriver());
    }

    public void removeAdsIfPresent() {
        JavascriptExecutor js = (JavascriptExecutor) DRIVER;

        String script = """
                const selectors = [
                  'iframe', 'ins', '.ad', '.ads', '.ad-container',
                  '[id*="ad"]', '[class*="ad"]',
                  '[id^="google_ads"]', '[class^="google_ads"]',
                  '[class*="amp"]', '[id*="banner"]', '[class*="pen"]', '[style*="z-index"]'
                ];
                selectors.forEach(sel => document.querySelectorAll(sel).forEach(el => el.remove()));
                """;

        js.executeScript(script);
    }
}
