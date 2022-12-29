package edu.petproject.statement.validator;

import edu.petproject.statement.exception.DocumentDataException;
import edu.petproject.statement.domain.Document;
import edu.petproject.statement.domain.document.DocumentDataResponse;
import edu.petproject.statement.exception.TransportException;

public interface DocumentChecker {
    DocumentDataResponse checkDocument (Document document) throws DocumentDataException, TransportException;
}
