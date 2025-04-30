package com.egt.core;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public abstract class Page {
    private final Browser browser;

    protected Page(Browser browser) {
        this.browser = browser;
    }
    public WebDriver getDriver() {
        return browser.getDriver();
    }
    public abstract boolean isOpened();
}
