package com.egt.factory;

import com.egt.core.enums.SubjectsType;
import com.egt.models.StudentUiModel;
import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.apache.logging.log4j.util.Strings.EMPTY;

/**
 * Utility class for creating test data instances of {@link StudentUiModel}.
 * <p>
 * Uses the {@link Faker} library to generate realistic and random values
 * </p>
 * <p>
 * The class is marked as a utility class using Lombok's {@code @UtilityClass},
 */
@UtilityClass
public class StudentFactory {
    private static final Faker faker = new Faker();
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);

    /**
     * Creates a {@link StudentUiModel} object populated with randomized but realistic values.
     *
     * @return a fully populated {@link StudentUiModel} for use in UI test scenarios
     */
    public static StudentUiModel createRandomStudent() {
        return StudentUiModel.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .mobile(faker.phoneNumber().subscriberNumber(10))
                .gender(EMPTY)
                .dateOfBirth(faker.date().birthday(18, 80)
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                        .format(DATE_FORMATTER))
                .subject(SubjectsType.ARTS.getLabel())
                .currentAddress(faker.address().streetAddress())
                .state(EMPTY)
                .city(EMPTY)
                .build();
    }
}
