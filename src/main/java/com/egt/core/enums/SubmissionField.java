package com.egt.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubmissionField {
    STUDENT_NAME("Student Name"),
    STUDENT_EMAIL("Student Email"),
    GENDER("Gender"),
    MOBILE("Mobile"),
    DATE_OF_BIRTH("Date of Birth"),
    SUBJECTS("Subjects"),
    ADDRESS("Address"),
    STATE_AND_CITY("State and City");

    private final String label;

}
