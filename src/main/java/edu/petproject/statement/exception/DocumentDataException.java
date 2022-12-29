package edu.petproject.statement.exception;

public class DocumentDataException extends Exception {
    private String code;
    public DocumentDataException(String code, String message) {
        super(message);
        this.code = code;
    }

    public DocumentDataException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
