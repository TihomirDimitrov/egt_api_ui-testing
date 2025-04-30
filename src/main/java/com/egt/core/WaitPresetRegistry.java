package com.egt.core;

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

    private final Map<String, Duration> timeouts = new HashMap<>();
    private final Map<String, Duration> intervals = new HashMap<>();

    public WaitPresetRegistry() {
        register("shortWait", Duration.ofSeconds(5), Duration.ofMillis(500));
        register("defaultWait", Duration.ofSeconds(10), Duration.ofMillis(500));
        register("longWait", Duration.ofSeconds(30), Duration.ofMillis(500));
    }

    public void register(String name, Duration timeout, Duration interval) {
        timeouts.put(name, timeout);
        intervals.put(name, interval);
    }

    /**
     * Returns FluentWait instance for particular WebDriver.
     *
     * @param waitName Името на пресета
     * @param driver   WebDriver instance
     * @return FluentWait instance
     * @throws IllegalArgumentException
     */
    public FluentWait<WebDriver> getWait(String waitName, WebDriver driver) {
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
