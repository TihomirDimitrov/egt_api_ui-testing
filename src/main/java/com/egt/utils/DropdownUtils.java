package com.egt.utils;

import com.egt.core.exceptions.DropdownException;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Utility class for interacting with dropdown options.
 */
@UtilityClass
public class DropdownUtils {

    /**
     * Clicks a random option from the provided list of WebElement.
     *
     * @param options list of visible dropdown options
     */
    public static WebElement selectRandomOption(List<WebElement> options) {
        if (options.isEmpty()) {
            throw new DropdownException("Dropdown is empty, no options presented!");
        }
        return options.stream()
                .findAny()
                .orElseThrow(() -> new DropdownException("Dropdown is empty, no options presented!"));
    }

    /**
     * Selects the first option in the dropdown.
     *
     * @param options list of visible dropdown options
     * @return first WebElement in dropdown
     */
    public static WebElement selectFirstOption(List<WebElement> options) {
        return options.stream()
                .findFirst()
                .orElseThrow(() -> new DropdownException("Dropdown is empty, no options presented!"));
    }

    /**
     * Selects an option matching the provided visible text (case-insensitive).
     *
     * @param options    list of visible dropdown options
     * @param optionText text to match
     * @return WebElement match by name
     */
    public static WebElement selectByVisibleText(List<WebElement> options, String optionText) {
       return options.stream()
                .filter(option -> option.getText().trim().equalsIgnoreCase(optionText))
                .findFirst()
                .orElseThrow(() -> new DropdownException("Dropdown option with text '" + optionText + "' not found"));

    }
}