package com.egt.core;

import com.egt.core.enums.WaitType;
import com.egt.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages the WebDriver lifecycle, navigation, and page object creation within the automation framework.
 * <p>
 * This class encapsulates logic related to:
 * <ul>
 *     <li>Lazy initialization of the WebDriver using a ThreadLocal instance</li>
 *     <li>Navigation to URLs and switching to specific page objects</li>
 *     <li>Centralized waiting functionality via WaitPresetRegistry</li>
 *     <li>Graceful browser closure and cleanup</li>
 * </ul>
 */
public class Browser {

    private static final Logger log = LoggerFactory.getLogger(Browser.class);
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> BROWSER_STATE = new ThreadLocal<>();

    private final WebDriverFactory webDriverFactory;
    private final PageObjectFactory pageObjectFactory;
    private final WaitPresetRegistry waitPresetRegistry;

    /**
     * Constructs a Browser instance with its required dependencies.
     *
     * @param webDriverFactory     factory for creating WebDriver instances
     * @param pageObjectFactory    factory for creating page objects
     * @param waitPresetRegistry   registry for providing FluentWait configurations
     */
    public Browser(WebDriverFactory webDriverFactory, PageObjectFactory pageObjectFactory,
                   WaitPresetRegistry waitPresetRegistry) {
        this.webDriverFactory = webDriverFactory;
        this.pageObjectFactory = pageObjectFactory;
        this.waitPresetRegistry = waitPresetRegistry;
    }

    /**
     * Lazily initializes and returns the current WebDriver instance for the thread.
     * If not already initialized, it creates and maximizes a new browser window.
     *
     * @return the WebDriver instance
     */
    public WebDriver getDriver() {
        if (DRIVER.get() == null) {
            WebDriver driver = webDriverFactory.createDriver();
            DRIVER.set(driver);
            BROWSER_STATE.set(false);
            driver.manage().window().maximize();
        }
        return DRIVER.get();
    }

    /**
     * Navigates to the specified URL using the current WebDriver instance.
     *
     * @param url the target URL to navigate to
     * @return the current Browser instance (for chaining)
     */
    public Browser navigateTo(String url) {
        this.getDriver().get(url);
        return this;
    }

    /**
     * Navigates to the specified URL and switches to the corresponding page object.
     * Also removes known ads from the DOM.
     *
     * @param url      the target URL
     * @param pageType the class of the page object to instantiate
     * @param <T>      a type extending Page
     * @return the initialized page object
     */
    public <T extends Page> T navigateTo(String url, Class<T> pageType) {
        this.getDriver().navigate().to(url);
        TestUtils.removeAds(getDriver());
        return this.switchToPage(pageType);
    }

    /**
     * Switches the current context to the specified page type using the page object factory.
     *
     * @param pageType the class of the desired page object
     * @param <T>      a type extending Page
     * @return the initialized page object
     */
    public <T extends Page> T switchToPage(Class<T> pageType) {
        return pageObjectFactory.create(pageType, this);
    }

    /**
     * Closes the WebDriver instance for the current thread.
     * Ensures proper cleanup of thread-local resources.
     */
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

    /**
     * Returns a configured FluentWait instance using the provided WaitType preset.
     *
     * @param waitType the type of wait to apply (SHORT, DEFAULT, LONG)
     * @return FluentWait instance bound to the current WebDriver
     */
    public FluentWait<WebDriver> wait(WaitType waitType) {
        return waitPresetRegistry.getWait(waitType, getDriver());
    }
}
