package com.inditex.domain.exceptions;

public class PriceNotFoundException extends Exception {
    public PriceNotFoundException() {
    }

    public PriceNotFoundException(String message) {
        super(message);
    }
}
