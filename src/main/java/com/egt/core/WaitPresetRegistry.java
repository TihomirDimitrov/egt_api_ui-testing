package com.egt.core;

import com.egt.core.enums.WaitType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.stereotype.Component;

/**
 * Registry for building FluentWait instances .
 */
@Component
public class WaitPresetRegistry {

    /**
     * Builds a FluentWait using timeout and interval defined in the WaitType enum.
     *
     * @param waitType the wait type
     * @param driver   WebDriver instance
     * @return FluentWait instance
     */
    public FluentWait<WebDriver> getWait(WaitType waitType, WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(waitType.getTimeout())
                .pollingEvery(waitType.getInterval())
                .ignoring(Exception.class);
    }
}
