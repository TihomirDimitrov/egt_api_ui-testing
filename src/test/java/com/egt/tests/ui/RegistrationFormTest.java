package com.egt.tests.ui;

import com.egt.base.ui.BaseUiTest;
import com.egt.factory.StudentFactory;
import com.egt.models.StudentUiModel;
import com.egt.pages.RegistrationFormPage;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationFormTest extends BaseUiTest {

    @Test(description = "Fill and submit registration form")
    public void testSubmitRegistrationForm() {
        RegistrationFormPage formPage = browser.navigateTo(urlConfig.getAutomationPracticeForm(), RegistrationFormPage.class);
        Assert.assertTrue(formPage.isOpened());
        StudentUiModel studentUiModel = StudentFactory.createRandomStudent();
        formPage.fillForm(studentUiModel).clickSubmit();

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(formPage.isModalOpened()).as("Modal should be opened").isTrue();
            softAssertions.assertThat(formPage.isSubmittedDataDisplayed(studentUiModel.getFirstName()))
                    .as("First Name is present").isTrue();
            softAssertions.assertThat(formPage.isSubmittedDataDisplayed(studentUiModel.getLastName()))
                    .as("Last Name is present").isTrue();
            softAssertions.assertThat(formPage.isSubmittedDataDisplayed(studentUiModel.getEmail()))
                    .as("Email is present").isTrue();
        });

        formPage.closeModal();
        SoftAssertions.assertSoftly(softly -> softly.assertThat(formPage.isModalClosed())
                .as("Modal should be closed").isTrue());

    }
}
