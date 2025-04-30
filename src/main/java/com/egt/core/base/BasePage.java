package com.egt.core.base;

import com.egt.core.Browser;
import com.egt.core.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public abstract class BasePage extends Page {

    protected BasePage(Browser browser) {
        super(browser);
    }

    public void removeAdsIfPresent() {
        WebDriver driver = getBrowser().getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "document.querySelectorAll('iframe, .ad, .ads, .ad-container," +
                        " [id*=\"ad\"], [class*=\"ad\"], [id*=\"google_ads\"], [class*=\"google_ads\"]')" +
                        ".forEach(function(el) { el.remove(); });"
        );
    }
}
