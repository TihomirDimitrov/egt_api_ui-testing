package com.egt.base.ui;

import com.egt.configuration.UrlConfig;
import com.egt.core.Browser;
import com.egt.core.PageObjectFactory;
import com.egt.core.WaitPresetRegistry;
import com.egt.core.WebDriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@SpringBootTest
public abstract class BaseUiTest extends AbstractTestNGSpringContextTests {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected WebDriverFactory webDriverFactory;

    @Autowired
    protected PageObjectFactory pageObjectFactory;

    @Autowired
    protected WaitPresetRegistry waitPresetRegistry;

    @Autowired
    protected UrlConfig urlConfig;

    protected Browser browser;

    @BeforeMethod
    public void setUp() {
        browser = new Browser(webDriverFactory, pageObjectFactory, waitPresetRegistry);
    }

    @AfterMethod
    public void tearDown() {
        log.info("Close the browser");
        if (browser != null) {
            browser.close();
        }
    }
}
