package com.egt.pages;

import com.egt.core.Browser;
import com.egt.core.base.BasePage;
import com.egt.core.enums.WaitType;
import com.egt.models.StudentUiModel;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        emailInput.sendKeys(studentUiModel.getEmail());
        maleGenderRadio.click();
        mobileInput.sendKeys(studentUiModel.getMobile());
        dateOfBirthInput.sendKeys(Keys.CONTROL + "a");
        dateOfBirthInput.sendKeys(studentUiModel.getDateOfBirth());
        dateOfBirthInput.sendKeys(Keys.ENTER);
        subjectsInput.sendKeys(studentUiModel.getSubject());
        subjectsInput.sendKeys(Keys.ENTER);
        hobbiesSportsCheckbox.click();
        hobbiesReadingCheckbox.click();
        currentAddressInput.sendKeys(studentUiModel.getCurrentAddress());
        stateInput.sendKeys(studentUiModel.getState());
        stateInput.sendKeys(Keys.ENTER);
        cityInput.sendKeys(studentUiModel.getCity());
        cityInput.sendKeys(Keys.ENTER);
        return this;
    }

    @Step("Click Submit Button")
    public void clickSubmit() {
        submitButton.click();
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
        getBrowser().scrollToElement(closeButton);
        this.wait(WaitType.SHORT.getPresetName()).until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }

    @Step("Verify Modal is Closed")
    public boolean isModalClosed() {
        try {
            return wait(WaitType.SHORT.getPresetName()).until(ExpectedConditions.invisibilityOf(modalTitle));
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public boolean isOpened() {
        return this.wait(WaitType.SHORT.getPresetName())
                .until(ExpectedConditions.visibilityOf(firstNameInput))
                .isDisplayed();
    }
}
