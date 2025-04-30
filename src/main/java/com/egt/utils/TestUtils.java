package com.egt.utils;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

@UtilityClass
public class TestUtils {
    public static void removeAds(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("""
            document.querySelectorAll(
                'iframe, .adsbygoogle, [id*="ad"], [class*="ad"], [id*="Ad"], [class*="Ad"], [id*="banner"]
                , [class*="banner"]').forEach(el => el.remove());""");
    }
}
