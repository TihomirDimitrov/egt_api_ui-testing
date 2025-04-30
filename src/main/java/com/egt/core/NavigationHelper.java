package com.egt.core;

import com.egt.configuration.UrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NavigationHelper {

    @Autowired
    private UrlConfig urlConfig;

    @Autowired
    private WebDriverFactory webDriverFactory;

    public void navigateToAutomationPracticeForm() {
        webDriverFactory.createDriver().get(urlConfig.getAutomationPracticeForm());
    }

//    public void navigateToLoginPage() {
//        webDriverFactory.createDriver().get(urlConfig.getLoginPage());
//    }

    public void navigateToHomePage() {
        webDriverFactory.createDriver().get(urlConfig.getHomePage());
    }
}
