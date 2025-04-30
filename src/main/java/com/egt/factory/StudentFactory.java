package com.egt.factory;

import com.egt.models.StudentUiModel;
import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StudentFactory {
    private static final Faker faker = new Faker();

    public static StudentUiModel createRandomStudent() {
        return StudentUiModel.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .mobile(faker.phoneNumber().subscriberNumber(10))
                .dateOfBirth(faker.date().birthday(18, 80).toString())
                .subject("Maths")
                .currentAddress(faker.address().streetAddress())
                .state("NCR")
                .city("Delhi")
                .build();
    }
}
