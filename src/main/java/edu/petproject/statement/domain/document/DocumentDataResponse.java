package edu.petproject.statement.domain.document;

public class DocumentDataResponse {
    boolean existing;

    public boolean isSuccess() {
        return existing;
    }

    public void setSuccess(boolean success) {
        this.existing = success;
    }

    @Override
    public String toString() {
        return "DocumentDataValidatorResponse{" +
                "existing=" + existing +
                '}';
    }
}
