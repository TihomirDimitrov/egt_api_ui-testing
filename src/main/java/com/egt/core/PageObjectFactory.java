package com.egt.core;

import com.egt.core.exceptions.PageCreationException;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
/**
 * Factory class for creating Page Object instances using <b><i>Java Reflection</b></i>.
 * <p>
 * Used by the {@link Browser} class to dynamically instantiate page objects
 * and initialize their WebElements via Selenium's {@link PageFactory}.
 *
 * <p>Each page class must:
 * <ul>
 *     <li>Extend {@link Page}</li>
 *     <li>Have a constructor that accepts a {@link Browser} argument</li>
 * </ul>
 *
 * Example usage:
 * <pre>{@code
 *   RegistrationFormPage page = pageObjectFactory.create(RegistrationFormPage.class, browser);
 * }</pre>
 */
@Component
public class PageObjectFactory {

    /**
     * Create an instance of Page Object and initialize its elements.
     *
     * @param pageType the class of the Page Object to create - must extend Page
     * @param browser current Browser instance
     * @param <T> generic type parameter which extending {@link Page}
     * @return page object instance
     */

    public <T extends Page> T create(Class<T> pageType, Browser browser) {
        try {
            T page = pageType.getDeclaredConstructor(Browser.class).newInstance(browser);
            PageFactory.initElements(browser.getDriver(), page);
            return page;
        } catch (NoSuchMethodException e) {
            throw new PageCreationException("No constructor found for " + pageType.getSimpleName() + " with Browser parameter.", e);
        } catch (InstantiationException e) {
            throw new PageCreationException("Cannot instantiate page class: " + pageType.getSimpleName(), e);
        } catch (IllegalAccessException e) {
            throw new PageCreationException("Cannot access constructor for page class: " + pageType.getSimpleName(), e);
        } catch (InvocationTargetException e) {
            throw new PageCreationException("Exception thrown inside constructor for page class: " + pageType.getSimpleName(), e);
        }
    }
}
