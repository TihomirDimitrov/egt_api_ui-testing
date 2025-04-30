package com.egt.listeners;

import com.egt.base.ui.BaseUiTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener extends BaseUiTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot();
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
