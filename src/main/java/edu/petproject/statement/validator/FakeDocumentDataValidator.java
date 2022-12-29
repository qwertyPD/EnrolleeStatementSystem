package edu.petproject.statement.validator;

import edu.petproject.statement.exception.DocumentDataException;
import edu.petproject.statement.domain.Document;
import edu.petproject.statement.domain.document.DocumentDataResponse;
import edu.petproject.statement.exception.TransportException;

public class FakeDocumentDataValidator implements DocumentChecker {

    // Якобы обращается в базу данных за проверкой паспортных данных

    private static final Integer truePassportSeria = 1000;
    private static final Integer trueDiplomaSeria = 2000;
    private static final Integer falsePassportSeria = 1001;
    private static final Integer falseDiplomaSeria = 2001;
    private static final Integer errPassportData = 1002;
    private static final Integer errDiplomaData = 2002;
    private static final Integer errTranspPassportData = 1003;
    private static final Integer errTranspDiplomaData = 2003;
    public DocumentDataResponse checkDocument(Document document) throws DocumentDataException, TransportException {

        DocumentDataResponse ddr = new DocumentDataResponse();

        if (document instanceof Document) {
            Document d = (Document) document;
            Integer documentSeria = d.getSeria();
            if (documentSeria.equals(truePassportSeria) || documentSeria.equals(trueDiplomaSeria)) {
                ddr.setSuccess(true);
            } else if (documentSeria.equals(falsePassportSeria) || documentSeria.equals(falseDiplomaSeria)) {
                ddr.setSuccess(false);
            } else if (documentSeria.equals(errPassportData) || documentSeria.equals(errDiplomaData)) {
                DocumentDataException ddex = new DocumentDataException("1", "FAKE DATABASE ERROR");
                throw ddex;
            } else if (documentSeria.equals(errTranspPassportData) || documentSeria.equals(errTranspDiplomaData)) {
                TransportException tex = new TransportException("FAKE TRANSPORT ERROR");
                throw tex;
            }
        }

        System.out.println(ddr);

        return ddr;
    }
}
