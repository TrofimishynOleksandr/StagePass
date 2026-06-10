package com.stagepass.exception;

public class SectionPriceNotFoundException extends RuntimeException {
    public SectionPriceNotFoundException(String section) {
        super("Price not specified for section: " + section);
    }
}
