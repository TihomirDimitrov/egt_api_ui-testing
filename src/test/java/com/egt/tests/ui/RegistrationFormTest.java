package com.egt.tests.ui;

import com.egt.base.ui.BaseUiTest;
import com.egt.factory.StudentFactory;
import com.egt.models.StudentUiModel;
import com.egt.pages.RegistrationFormPage;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.Map;

import static com.egt.core.enums.SubmissionField.*;

public class RegistrationFormTest extends BaseUiTest {

    @Test(description = "Fill out and submit registration form")
    public void testSubmitRegistrationForm() {
        RegistrationFormPage formPage = browser.navigateTo(urlConfig.getAutomationPracticeForm()
                , RegistrationFormPage.class);
        Assertions.assertThat(formPage.isOpened())
                .as("Registration form page is not opened successfully!").isTrue();
        StudentUiModel studentUiModel = StudentFactory.createRandomStudent();
        formPage.fillForm(studentUiModel)
                .clickSubmit();

        Map<String, String> actualData = formPage.getSubmittedData();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(formPage.isModalOpened()).as("Modal is not opened!").isTrue();

            softly.assertThat(actualData.get(STUDENT_NAME.getLabel()))
                    .as("Student verification failed, not expected student name!")
                    .isEqualTo(studentUiModel.getFirstName() + " " + studentUiModel.getLastName());

            softly.assertThat(actualData.get(STUDENT_EMAIL.getLabel()))
                    .as("Email verification failed, not expected email value!")
                    .isEqualTo(studentUiModel.getEmail());

            softly.assertThat(actualData.get(GENDER.getLabel()))
                    .as("Gender verification failed, not expected gender value!")
                    .isEqualTo(studentUiModel.getGender());

            softly.assertThat(actualData.get(MOBILE.getLabel()))
                    .as("Phone number verification failed, not expected value!")
                    .isEqualTo(studentUiModel.getMobile());

            softly.assertThat(actualData.get(DATE_OF_BIRTH.getLabel()))
                    .as("Birth date verification failed, not expected value!")
                    .contains(studentUiModel.getDateOfBirth());

            softly.assertThat(actualData.get(SUBJECTS.getLabel()))
                    .as("Subject verification failed, not expected value!")
                    .isEqualTo(studentUiModel.getSubject());

            softly.assertThat(actualData.get(ADDRESS.getLabel()))
                    .as("Address verification failed, not expected value!")
                    .isEqualTo(studentUiModel.getCurrentAddress());

            String expectedStateAndCity = studentUiModel.getState() + " " + studentUiModel.getCity();
            softly.assertThat(formPage.isSubmittedDataDisplayed(expectedStateAndCity.trim()))
                    .overridingErrorMessage("State and city verification failed, not expected values!")
                    .isTrue();
        });

        formPage.closeModal();
        SoftAssertions.assertSoftly(softly -> softly.assertThat(formPage.isModalClosed())
                .overridingErrorMessage("Modal should be closed").isTrue());
    }

    @Test(description = "Submit form without required fields and validate field errors")
    public void testRegistrationFormRequiredFields() {
        RegistrationFormPage formPage = browser.navigateTo(urlConfig.getAutomationPracticeForm(), RegistrationFormPage.class);

        Assertions.assertThat(formPage.isOpened())
                .as("Registration form page is not opened successfully!").isTrue();

        formPage.clickSubmit();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(formPage.isFieldRequired(formPage.getFirstNameInput()))
                    .as("First Name input should be marked as required").isTrue();

            softly.assertThat(formPage.isFieldRequired(formPage.getLastNameInput()))
                    .as("Last Name input should be marked as required").isTrue();

            softly.assertThat(formPage.isFieldRequired(formPage.getMobileInput()))
                    .as("Mobile input should be marked as required").isTrue();

            softly.assertThat(formPage.isFieldRequired(formPage.getMaleGenderRadio()))
                    .as("Gender should be marked as required!").isTrue();
        });
    }
}
