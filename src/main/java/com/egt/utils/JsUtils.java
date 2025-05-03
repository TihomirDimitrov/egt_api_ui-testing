package com.egt.utils;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@UtilityClass
public class JsUtils {

    /**
     * Clears the value of an input field using JavaScript.
     *
     * @param driver  the WebDriver instance
     * @param element the WebElement to clear
     */
    public static void clearInputWithJs(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
    }

    /**
     * Removes the floating ad banner with id 'fixedban' using JavaScript.
     *
     * @param driver the WebDriver instance
     */
    public static void removeFixedBanner(WebDriver driver) {
        ((JavascriptExecutor) driver)
                .executeScript("document.getElementById('fixedban')?.remove();");
    }

    /**
     * Removes common ad elements from the DOM using JavaScript execution.
     * <p>
     * This method targets a wide range of known advertisement-related elements, such as:
     * <ul>
     *     <li>&lt;iframe&gt; tags</li>
     *     <li>Elements with classes like adsbygoogle, .ad-container, .sponsored</li>
     *     <li>Elements with IDs or classes that start with or contain <code>ad</code></li>
     * </ul>
     * It's useful when ads may block important elements or cause flakiness during interactions.
     * </p>
     *
     * @param driver the {@link WebDriver} instance used to execute the JavaScript
     */
    public static void removeAds(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String script = """
                document.querySelectorAll(
                    'iframe, .adsbygoogle, .ad-container, .google-ad, .sponsor, .sponsored, .ad-slot, [id^="ad_"], [id^="ads_"], [class^="ad-"], [class*=" ad-"], [class*="-ad "]'
                ).forEach(el => el.remove());
                """;

        js.executeScript(script);
    }

}
