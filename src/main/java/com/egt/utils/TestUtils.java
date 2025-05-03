package com.egt.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BooleanSupplier;

@Slf4j
@UtilityClass
public class TestUtils {

    /**
     * Evaluates a condition and executes an action only if the condition is true.
     * Returns true if the action was executed successfully, false otherwise.
     *
     * @param condition the condition to evaluate
     * @param action    the action to execute if the condition is true
     */
    public static void performIf(BooleanSupplier condition, Runnable action) {
        try {
            if (condition.getAsBoolean()) {
                action.run();
            }
        } catch (Exception e) {
            log.info("performIf failed: " + e.getMessage());
        }
    }

    /**
     * Executes one of two actions depending on the result of a boolean condition.
     * If the condition is true, runs the main action; otherwise runs the elseAction.
     *
     * @param condition  the condition to evaluate
     * @param action     the action to run if condition is true
     * @param elseAction the action to run if condition is false or fails
     */
    public static void performIfElse(BooleanSupplier condition, Runnable action, Runnable elseAction) {
        try {
            if (condition.getAsBoolean()) {
                action.run();
            } else {
                elseAction.run();
            }
        } catch (Exception e) {
            log.warn("performIf failed: {}", e.getMessage(), e);
            elseAction.run();
        }
    }
}
