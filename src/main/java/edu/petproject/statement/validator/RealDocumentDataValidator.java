package edu.petproject.statement.validator;

import edu.petproject.statement.exception.DocumentDataException;
import edu.petproject.statement.domain.Document;
import edu.petproject.statement.domain.document.DocumentDataResponse;

public class RealDocumentDataValidator implements DocumentChecker {
    public DocumentDataResponse checkDocument(Document document) throws DocumentDataException {
        return null;
    }
}
