package com.egt.core.base;

import com.egt.core.Browser;
import com.egt.core.Page;
import com.egt.core.enums.WaitType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.Predicate;

public abstract class BasePage extends Page {
    protected Actions actions;

    protected BasePage(Browser browser) {
        super(browser);
        this.actions = new Actions(browser.getDriver());
    }

    protected void scrollToElement(WebElement element) {
        actions.scrollToElement(element).perform();
    }

    public void scrollToElementJs(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'nearest'});"
                , element);
    }

    protected void waitUntilElementClickable(WebElement element, WaitType waitType) {
        wait(waitType).until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void smartClick(WebElement element, WaitType waitType) {
        scrollToElement(element);
        waitUntilElementClickable(element, waitType);
        element.click();
    }

    protected void smartSendKeys(WebElement element, String value, WaitType waitType) {
        scrollToElement(element);
        waitUntilElementClickable(element, waitType);
        element.sendKeys(value);
    }

    protected <T> void performIf(Predicate<T> condition, T value, Runnable action) {
        if (condition.test(value)) {
            action.run();
        }
    }
}
