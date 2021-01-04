package ru.yoursweet667.uno.web.model;

public class Error {
    /**
     * Error description
     */
    private final String errorMessage;

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
