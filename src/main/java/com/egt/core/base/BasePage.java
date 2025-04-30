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
