package com.egt.core;

import com.egt.core.enums.WaitType;
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
    protected FluentWait<WebDriver> wait(WaitType waitType) {
        synchronized (browser) {
            return browser.wait(waitType);
        }
    }
    public abstract boolean isOpened();
}
