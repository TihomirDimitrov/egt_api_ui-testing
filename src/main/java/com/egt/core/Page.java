package com.egt.core;

import com.egt.core.enums.WaitType;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

/**
 * Abstract base class representing a web page in the test framework.
 * <p>
 * All page objects in the system should extend this class. It provides access
 * to the shared {@link Browser} instance, WebDriver, and centralized waiting mechanisms.
 * </p>
 * <p>
 * The class follows the Page Object Model (POM) design pattern and ensures each page
 * defines its own implementation of {@link #isOpened()} for validation.
 * </p>
 */
@Getter
public abstract class Page {

    /**
     * Browser wrapper that contains the WebDriver and wait configurations.
     */
    private final Browser browser;

    /**
     * Constructs a new page instance with a reference to the shared {@link Browser}.
     *
     * @param browser the browser instance to be used for interacting with the page
     */
    protected Page(Browser browser) {
        this.browser = browser;
    }

    /**
     * Returns the current WebDriver from the browser.
     *
     * @return the active WebDriver instance
     */
    public WebDriver getDriver() {
        return browser.getDriver();
    }

    /**
     * Returns a FluentWait instance for the given {@link WaitType}.
     *
     * @param waitType the type of wait preset (e.g., SHORT, DEFAULT, LONG)
     * @return a configured FluentWait for the current WebDriver
     */
    protected FluentWait<WebDriver> wait(WaitType waitType) {
        synchronized (browser) {
            return browser.wait(waitType);
        }
    }

    /**
     * Checks if the current page is considered 'opened'.
     * Each concrete page class must implement this method to define its opening condition.
     *
     * @return true if the page is opened, false otherwise
     */
    public abstract boolean isOpened();
}
