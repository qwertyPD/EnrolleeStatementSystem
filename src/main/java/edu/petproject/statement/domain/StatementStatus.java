package edu.petproject.statement.domain;

public enum StatementStatus {
    START, CHECKED;

    public static StatementStatus fromValue(int value) {
        for (StatementStatus ss: StatementStatus.values()) {
            if (ss.ordinal() == value) {
                return ss;
            } else {

            }
        } throw new RuntimeException("Unknown value: " + value);
    }
}
