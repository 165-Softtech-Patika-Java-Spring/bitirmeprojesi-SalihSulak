package com.softtech.marketapi.enums.errors;

import com.softtech.marketapi.generic.enums.BaseErrorMessage;

public enum ProductErrorMessages implements BaseErrorMessage {
    PRODUCT_NOT_FOUND("Product not found!"),
    PRODUCT_PRICE_NOT_POSITIVE("Product price must be positive");

    private String message;

    ProductErrorMessages(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
