package com.nate.exceptions;

public class InvalidUpdateField extends RuntimeException {
    public InvalidUpdateField() {
        super("Invalid update field");
    }

}
