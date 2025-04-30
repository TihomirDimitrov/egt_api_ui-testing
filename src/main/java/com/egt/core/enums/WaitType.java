package com.egt.core.enums;

import lombok.Getter;

@Getter
public enum WaitType {
    SHORT("shortWait"),
    DEFAULT("defaultWait"),
    LONG("longWait");

    private final String presetName;

    WaitType(String presetName) {
        this.presetName = presetName;
    }

}
