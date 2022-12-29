package edu.petproject.statement.validator;

import edu.petproject.statement.exception.DocumentDataException;
import edu.petproject.statement.domain.Document;
import edu.petproject.statement.domain.document.AnswerDocumentData;
import edu.petproject.statement.domain.Diploma;
import edu.petproject.statement.domain.document.AnswerDocumentDataItem;
import edu.petproject.statement.domain.document.DocumentDataResponse;
import edu.petproject.statement.domain.StatementData;
import edu.petproject.statement.exception.TransportException;

public class DocumentDataValidator {
    private static final String codeError = "NO CONN";
    private FakeDocumentDataValidator documentChecker;
    public DocumentDataValidator() {
        documentChecker = new FakeDocumentDataValidator();
    }
    public AnswerDocumentData checkDocumentData(StatementData statementData) {
        AnswerDocumentData answer = new AnswerDocumentData();

        answer.addItems(checkDocument(statementData.getPassport()));          // Проверка паспорта

        for (Diploma d: statementData.getDiploma()) {                           // Проверка дипломов
            answer.addItems(checkDocument(d));
        }

        return answer;
    }

    public AnswerDocumentDataItem checkDocument(Document document) {
        AnswerDocumentDataItem.DocumentStatus status = null;
        AnswerDocumentDataItem.DocumentError error = null;

        try {
            DocumentDataResponse ddr = documentChecker.checkDocument(document);
            status = ddr.isSuccess() ?
                    AnswerDocumentDataItem.DocumentStatus.YES : AnswerDocumentDataItem.DocumentStatus.NO;
        } catch (DocumentDataException ex) {
            ex.printStackTrace(System.out);
            status = AnswerDocumentDataItem.DocumentStatus.ERROR;
            error = new AnswerDocumentDataItem.DocumentError(ex.getCode(), ex.getMessage());
        } catch (TransportException ex) {
            ex.printStackTrace(System.out);
            status = AnswerDocumentDataItem.DocumentStatus.ERROR;
            error = new AnswerDocumentDataItem.DocumentError(codeError, ex.getMessage());
        }

        AnswerDocumentDataItem answer = new AnswerDocumentDataItem(document, status, error);
        return null;
    }
}
