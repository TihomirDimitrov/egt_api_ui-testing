package com.egt.core;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

@Getter
public abstract class Page {
    private final Browser browser;

    protected Page(Browser browser) {
        this.browser = browser;
    }
    public WebDriver getDriver() {
        return browser.getDriver();
    }
    protected FluentWait<WebDriver> wait(String presetName) {
        synchronized (browser) {
            return browser.wait(presetName);
        }
    }
    public abstract boolean isOpened();
}
