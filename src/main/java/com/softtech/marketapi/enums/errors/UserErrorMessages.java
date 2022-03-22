package com.softtech.marketapi.enums.errors;

import com.softtech.marketapi.generic.enums.BaseErrorMessage;

public enum UserErrorMessages implements BaseErrorMessage {
    USER_NOT_FOUND("User not found!"),
    USER_SAVE_FAILED("User save failed");

    private String message;

    UserErrorMessages(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
