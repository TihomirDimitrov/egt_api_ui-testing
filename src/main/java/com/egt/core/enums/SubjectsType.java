package com.egt.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing supported school subjects that can be selected in a student form.
 */
@Getter
@AllArgsConstructor
public enum SubjectsType {
    MATHS("Maths"),
    PHYSICS("Physics"),
    CHEMISTRY("Chemistry"),
    ENGLISH("English"),
    COMPUTER_SCIENCE("Computer Science"),
    ECONOMICS("Economics"),
    HISTORY("History"),
    GEOGRAPHY("Geography"),
    BIOLOGY("Biology"),
    ARTS("Arts");

    private final String label;

}
