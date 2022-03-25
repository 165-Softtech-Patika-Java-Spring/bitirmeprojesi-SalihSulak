package com.softtech.marketapi.enums.errors;

import com.softtech.marketapi.generic.enums.BaseErrorMessage;

public enum VatRateErrorMessages implements BaseErrorMessage {
    VAT_RATE_NOT_POSITIVE("Vat percentage must be positive");

    private String message;

    VatRateErrorMessages(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
