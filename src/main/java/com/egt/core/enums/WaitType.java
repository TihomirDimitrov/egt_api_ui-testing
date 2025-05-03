package com.egt.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

/**
 * Enum defining wait types with their associated timeout and polling interval.
 */
@Getter
@AllArgsConstructor
public enum WaitType {

    SHORT(Duration.ofSeconds(5), Duration.ofMillis(500)),
    DEFAULT(Duration.ofSeconds(10), Duration.ofMillis(500)),
    LONG(Duration.ofSeconds(30), Duration.ofMillis(500));

    private final Duration timeout;
    private final Duration interval;

}
