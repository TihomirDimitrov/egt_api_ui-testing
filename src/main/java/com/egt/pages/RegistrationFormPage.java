package com.egt.pages;

import com.egt.core.Browser;
import com.egt.core.base.BasePage;
import com.egt.core.enums.WaitType;
import com.egt.models.StudentUiModel;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.egt.core.enums.WaitType.SHORT;
import static com.egt.utils.TestUtils.performIf;
import static com.egt.utils.TestUtils.removeAds;

public class RegistrationFormPage extends BasePage {

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(xpath = "//label[text()='Male']")
    private WebElement maleGenderRadio;

    @FindBy(id = "userNumber")
    private WebElement mobileInput;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInput;

    @FindBy(id = "subjectsInput")
    private WebElement subjectsInput;

    @FindBy(xpath = "//label[text()='Sports']")
    private WebElement hobbiesSportsCheckbox;

    @FindBy(xpath = "//label[text()='Reading']")
    private WebElement hobbiesReadingCheckbox;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressInput;

    @FindBy(id = "react-select-3-input")
    private WebElement stateInput;

    @FindBy(id = "react-select-4-input")
    private WebElement cityInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement modalTitle;

    @FindBy(className = "table-responsive")
    private WebElement resultTable;

    @FindBy(id = "closeLargeModal")
    private WebElement closeButton;

    public RegistrationFormPage(Browser browser) {
        super(browser);
    }

    @Step("Fill Registration Form")
    public RegistrationFormPage fillForm(StudentUiModel studentUiModel) {
        firstNameInput.sendKeys(studentUiModel.getFirstName());
        lastNameInput.sendKeys(studentUiModel.getLastName());
        smartSendKeys(emailInput, studentUiModel.getEmail(), SHORT);
        performIf(() -> isElementClickable(maleGenderRadio), () ->
        {
            removeAds(getDriver());
            maleGenderRadio.click();
        });
        smartSendKeys(mobileInput, studentUiModel.getMobile(), SHORT);
        smartSendKeys(dateOfBirthInput, studentUiModel.getDateOfBirth(), SHORT);
        dateOfBirthInput.sendKeys(Keys.ENTER);
        smartSendKeys(subjectsInput, studentUiModel.getSubject(), SHORT);
        subjectsInput.sendKeys(Keys.ENTER);
        smartClick(hobbiesSportsCheckbox, SHORT);
        smartClick(hobbiesReadingCheckbox, SHORT);
        smartSendKeys(currentAddressInput, studentUiModel.getCurrentAddress(), SHORT);
        smartSendKeys(stateInput, studentUiModel.getState(), SHORT);
        stateInput.sendKeys(Keys.ENTER);
        smartSendKeys(cityInput, studentUiModel.getCity(), SHORT);
        cityInput.sendKeys(Keys.ENTER);

        return this;
    }

    @Step("Click Submit Button")
    public void clickSubmit() {
        scrollToElementJs(submitButton);
        performIf(() -> isElementClickable(submitButton), () -> submitButton.click());
    }

    @Step("Check if Modal is Opened")
    public boolean isModalOpened() {
        return modalTitle.isDisplayed();
    }

    @Step("Verify Data in Modal")
    public boolean isSubmittedDataDisplayed(String expectedData) {
        return resultTable.getText().contains(expectedData);
    }

    @Step("Close Modal")
    public void closeModal() {
        ((JavascriptExecutor) getDriver())
                .executeScript("document.getElementById('fixedban')?.remove();");

        scrollToElement(closeButton);
        wait(WaitType.SHORT).until(ExpectedConditions.elementToBeClickable(closeButton));
        closeButton.click();
    }


    @Step("Verify Modal is Closed")
    public boolean isModalClosed() {
        try {
            return wait(SHORT).until(ExpectedConditions.invisibilityOf(modalTitle));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isOpened() {
        return this.wait(SHORT)
                .until(ExpectedConditions.visibilityOf(firstNameInput))
                .isDisplayed();
    }
}
