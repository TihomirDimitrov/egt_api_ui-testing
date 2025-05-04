package com.egt.pages;

import com.egt.core.Browser;
import com.egt.core.base.BasePage;
import com.egt.core.enums.WaitType;
import com.egt.models.StudentUiModel;
import com.egt.utils.DropdownUtils;
import io.qameta.allure.Allure;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.egt.core.enums.WaitType.SHORT;
import static com.egt.utils.JsUtils.removeAds;
import static com.egt.utils.JsUtils.removeFixedBanner;
import static com.egt.utils.TestUtils.performIf;

@Getter
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

    @FindBy(id = "state")
    private WebElement stateDropdown;

    @FindBy(id = "react-select-3-input")
    private WebElement stateInput;

    @FindBy(id = "city")
    private WebElement cityDropdown;

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

    @FindBy(css = "div[id='state'] div[class*='control']")
    private List<WebElement> options;

    public RegistrationFormPage(Browser browser) {
        super(browser);
    }

    public RegistrationFormPage fillForm(StudentUiModel studentUiModel) {
        Allure.step("Fill Registration Form");
        firstNameInput.sendKeys(studentUiModel.getFirstName());
        lastNameInput.sendKeys(studentUiModel.getLastName());
        smartSendKeys(emailInput, studentUiModel.getEmail(), SHORT);
        performIf(() -> isElementClickable(maleGenderRadio), () ->
        {
            removeAds(getDriver());
            maleGenderRadio.click();
        });
        studentUiModel.setGender(maleGenderRadio.getText());
        smartSendKeys(mobileInput, studentUiModel.getMobile(), SHORT);
        smartSendKeysWithJsClean(dateOfBirthInput, studentUiModel.getDateOfBirth());
        smartSendKeys(subjectsInput, studentUiModel.getSubject(), SHORT);
        subjectsInput.sendKeys(studentUiModel.getSubject(), Keys.ENTER);
        smartClick(hobbiesSportsCheckbox, SHORT);
        smartClick(hobbiesReadingCheckbox, SHORT);
        smartSendKeys(currentAddressInput, studentUiModel.getCurrentAddress(), SHORT);
        selectRandomState(studentUiModel);
        selectRandomCity(studentUiModel);
        return this;
    }

    public void clickSubmit() {
        Allure.step("Click Submit Button");
        scrollToElementJs(submitButton);
        performIf(() -> isElementClickable(submitButton), () -> submitButton.click());
    }

    public boolean isModalOpened() {
        Allure.step("Check if Modal is Opened");
        return modalTitle.isDisplayed();
    }

    public boolean isSubmittedDataDisplayed(String expectedData) {
        Allure.step("Verify Data in Modal");
        return resultTable.getText().contains(expectedData);
    }

    public void closeModal() {
        Allure.step("Close Modal");
        removeFixedBanner(getDriver());
        scrollToElement(closeButton);
        wait(WaitType.SHORT).until(ExpectedConditions.elementToBeClickable(closeButton));
        closeButton.click();
    }

    public boolean isModalClosed() {
        Allure.step("Verify Modal is Closed");
        try {
            return wait(SHORT).until(ExpectedConditions.invisibilityOf(modalTitle));
        } catch (Exception e) {
            return false;
        }
    }

    private void selectRandomState(StudentUiModel studentUiModel) {
        Allure.step("Select random state from dropdown");
        smartClick(stateDropdown, SHORT);
        WebElement selectedOption = DropdownUtils.selectRandomOption(options);
        studentUiModel.setCity(selectedOption.getText());
        stateInput.sendKeys(Keys.ENTER);
    }

    private void selectRandomCity(StudentUiModel studentUiModel) {
        Allure.step("Select random city from dropdown");
        smartClick(cityDropdown, SHORT);
        WebElement selectedOption = DropdownUtils.selectFirstOption(options);
        studentUiModel.setCity(selectedOption.getText());
        cityInput.sendKeys(Keys.ENTER);
    }

    public Map<String, String> getSubmittedData() {
        Allure.step("Get table submitted data");
        Map<String, String> dataMap = new HashMap<>();
        List<WebElement> rows = resultTable.findElements(By.cssSelector("tbody tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() == 2) {
                String label = cells.get(0).getText().trim();
                String value = cells.get(1).getText().trim();
                dataMap.put(label, value);
            }
        }
        return dataMap;
    }

    public boolean isFieldRequired(WebElement element) {
        Allure.step("Check if field is marked as required and cover by red border");
        scrollToElement(element);
        String borderColor = element.getCssValue("border-color");
        return borderColor.equals("rgb(220, 53, 69)");
    }

    @Override
    public boolean isOpened() {
        Allure.step("Verify if " + RegistrationFormPage.class.getSimpleName() + " is opened");
        return this.wait(SHORT)
                .until(ExpectedConditions.visibilityOf(firstNameInput))
                .isDisplayed();
    }
}
