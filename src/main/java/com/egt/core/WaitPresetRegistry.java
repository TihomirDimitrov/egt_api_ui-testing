package com.egt.core;

import com.egt.core.enums.WaitType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Register for FluentWait, allows to wait in позволяващ централизирано управление на изчакванията.
 */
@Component
public class WaitPresetRegistry {

    private final Map<WaitType, Duration> timeouts = new HashMap<>();
    private final Map<WaitType, Duration> intervals = new HashMap<>();

    public WaitPresetRegistry() {
        register(WaitType.SHORT, Duration.ofSeconds(5), Duration.ofMillis(500));
        register(WaitType.DEFAULT, Duration.ofSeconds(10), Duration.ofMillis(500));
        register(WaitType.LONG, Duration.ofSeconds(30), Duration.ofMillis(500));
    }

    public void register(WaitType waitType, Duration timeout, Duration interval) {
        timeouts.put(waitType, timeout);
        intervals.put(waitType, interval);
    }

    /**
     * Returns FluentWait instance for particular WebDriver.
     *
     * @param waitName Името на пресета
     * @param driver   WebDriver instance
     * @return FluentWait instance
     * @throws IllegalArgumentException
     */
    public FluentWait<WebDriver> getWait(WaitType waitName, WebDriver driver) {
        Duration timeout = timeouts.get(waitName);
        Duration interval = intervals.get(waitName);

        if (timeout == null || interval == null) {
            throw new IllegalArgumentException("No such wait preset: " + waitName);
        }

        return new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(interval)
                .ignoring(Exception.class);
    }
}
