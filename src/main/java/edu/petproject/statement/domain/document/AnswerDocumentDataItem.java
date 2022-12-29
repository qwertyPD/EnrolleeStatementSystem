package edu.petproject.statement.domain.document;

import edu.petproject.statement.domain.Document;
import edu.petproject.statement.domain.Person;

public class AnswerDocumentDataItem {
    private Document document;
    private DocumentStatus status;
    private DocumentError error;
    public enum DocumentStatus {
        YES, NO, ERROR;
    }

    public static class DocumentError {
        private String code;
        private String text;

        public DocumentError(String code, String text) {
            this.code = code;
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public String getText() {
            return text;
        }
    }

    public AnswerDocumentDataItem(Document document, DocumentStatus status, DocumentError error) {
        this.document = document;
        this.status = status;
        this.error = error;
    }

    public Document getDocument() {
        return document;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public DocumentError getError() {
        return error;
    }
}
