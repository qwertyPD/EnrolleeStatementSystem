package edu.petproject.statement.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StatementData {
    protected long statementDataId;
    protected LocalDateTime statementDate;
    protected StatementStatus statementStatus;
    protected Person person;
    protected Passport passport;
    protected List<Diploma> diploma;

    public long getStatementDataId() {
        return this.statementDataId;
    }

    public void setStatementDataId(long statementDataId) {
        this.statementDataId = statementDataId;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Passport getPassport() {
        return this.passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Diploma> getDiploma() {
        return diploma;
    }

    public LocalDateTime getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(LocalDateTime statementDate) {
        this.statementDate = statementDate;
    }

    public StatementStatus getStatementStatus() {
        return statementStatus;
    }

    public void setStatementStatus(StatementStatus statementStatus) {
        this.statementStatus = statementStatus;
    }

    public void addDiploma(Diploma dipl) {
        if (diploma == null) {
            diploma = new ArrayList<Diploma>(10);
        }
            diploma.add(dipl);
    }

    @Override
    public String toString() {
        return "StatementData{" +
                "statementDataId=" + statementDataId +
                ", statementDate=" + statementDate +
                ", statementStatus=" + statementStatus +
                ", person=" + person +
                ", passport=" + passport +
                ", diploma=" + diploma +
                '}';
    }
}
