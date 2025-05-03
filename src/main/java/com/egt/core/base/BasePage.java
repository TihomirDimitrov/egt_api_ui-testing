package com.egt.core.base;

import com.egt.core.Browser;
import com.egt.core.Page;
import com.egt.core.enums.WaitType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.egt.core.enums.WaitType.SHORT;
import static com.egt.utils.JsUtils.clearInputWithJs;

/**
 * Abstract base class for all page objects.
 * Provides common UI interaction methods such as scrolling, clicking, sending keys, etc.
 */
public abstract class BasePage extends Page {
    protected Actions actions;

    protected BasePage(Browser browser) {
        super(browser);
        this.actions = new Actions(browser.getDriver());
    }

    /**
     * Scrolls to the specified element using the Actions API.
     *
     * @param element the element to scroll to
     */

    protected void scrollToElement(WebElement element) {
        actions.scrollToElement(element).perform();
    }

    /**
     * Scrolls to the element using JavaScript, center it in view
     *
     * @param element the element to scroll to
     */
    public void scrollToElementJs(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'nearest'});"
                , element);
    }

    /**
     * Waits until the specified element is clickable using the given wait type.
     *
     * @param element  the element to wait for
     * @param waitType the predefined wait duration
     */
    protected void waitUntilElementClickable(WebElement element, WaitType waitType) {
        wait(waitType).until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Method for clicking on particular element, but before that scroll to it and wait until element is clickable.
     *
     * @param element  the element to click
     * @param waitType the predefined wait type
     */
    protected void smartClick(WebElement element, WaitType waitType) {
        scrollToElementJs(element);
        waitUntilElementClickable(element, waitType);
        element.click();
    }

    /**
     * Method for SendKeys on particular element, but before that scroll to it and wait until element is clickable.
     *
     * @param element  the element to click
     * @param waitType the predefined wait type
     */
    protected void smartSendKeys(WebElement element, String value, WaitType waitType) {
        scrollToElement(element);
        waitUntilElementClickable(element, waitType);
        element.sendKeys(value);
    }

    /**
     * Sends keys to an element in a smart way: scrolls to it, waits until it's clickable,
     * clears existing content, and then types the provided value.
     *
     * @param element the WebElement to interact with
     * @param value   the text to input
     */
    protected void smartSendKeysWithJsClean(WebElement element, String value) {
        scrollToElement(element);
        waitUntilElementClickable(element, SHORT);
        element.click();
        clearInputWithJs(getDriver(), element);
        element.sendKeys(value);
        element.sendKeys(Keys.ENTER);
    }

    /**
     * Check if element is clickable with wait
     *
     * @param element particular element
     * @return true if element is clickable
     */

    protected boolean isElementClickable(WebElement element) {
        try {
            wait(SHORT).until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
