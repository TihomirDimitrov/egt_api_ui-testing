package com.egt.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class WaitPresetRegistry {

    private final Map<String, FluentWait<WebDriver>> presets = new HashMap<>();

    public WaitPresetRegistry() {
        presets.put("shortWait", createWait(Duration.ofSeconds(5), Duration.ofMillis(500)));
        presets.put("defaultWait", createWait(Duration.ofSeconds(10), Duration.ofMillis(500)));
        presets.put("longWait", createWait(Duration.ofSeconds(30), Duration.ofMillis(500)));
    }

    private FluentWait<WebDriver> createWait(Duration timeout, Duration polling) {
        return new FluentWait<WebDriver>(null)
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(Exception.class);
    }
}
