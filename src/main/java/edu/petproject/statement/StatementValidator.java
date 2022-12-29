package edu.petproject.statement;

import edu.petproject.statement.dao.StatementDaoImpl;
import edu.petproject.statement.domain.document.AnswerDocumentData;
import edu.petproject.statement.domain.StatementData;
import edu.petproject.statement.exception.DaoException;
import edu.petproject.statement.validator.DocumentDataValidator;

import java.util.LinkedList;
import java.util.List;

public class StatementValidator {
    private DocumentDataValidator passportDataVal;
    private DocumentDataValidator diplomaDataVal;

    public StatementValidator() {
        passportDataVal = new DocumentDataValidator();
        diplomaDataVal = new DocumentDataValidator();
    }

    public static void main(String[] args) throws DaoException {
        StatementValidator sv = new StatementValidator();
        sv.checkAll();
    }

    public void checkAll() {
        try {
            List<StatementData> statementDataList = readStatementData();

            for (StatementData sd: statementDataList) {
                checkOneStatement(sd);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void checkOneStatement(StatementData statement) {
        AnswerDocumentData answerDocumentData = checkPassportData(statement);
        AnswerDocumentData answerDiplomaData = checkDiplomaData(statement);
    }

    public List<StatementData> readStatementData() throws DaoException {
        return new StatementDaoImpl().getStatementDataFromDB();
    }

    public AnswerDocumentData checkPassportData(StatementData statementData) {
        System.out.println("Check passport is running");
        return passportDataVal.checkDocumentData(statementData);
    }

    public AnswerDocumentData checkDiplomaData(StatementData statementData) {
        System.out.println("Check diploma is running");
        return diplomaDataVal.checkDocumentData(statementData);
    }
}