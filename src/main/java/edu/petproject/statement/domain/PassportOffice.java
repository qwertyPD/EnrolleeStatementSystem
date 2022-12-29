package edu.petproject.statement.domain;

public class PassportOffice {
    private int passOfficeId;
    private String passOfficeName;

    public PassportOffice() {
    }

    public PassportOffice(int passOfficeId, String passOfficeName) {
        this.passOfficeId = passOfficeId;
        this.passOfficeName = passOfficeName;
    }

    public int getPassOfficeId() {
        return passOfficeId;
    }

    public void setPassOfficeId(int passOfficeId) {
        this.passOfficeId = passOfficeId;
    }

    public String getPassOfficeName() {
        return passOfficeName;
    }

    public void setPassOfficeName(String passOfficeName) {
        this.passOfficeName = passOfficeName;
    }

    @Override
    public String toString() {
        return "PassportOffice{" +
                "passOfficeId=" + passOfficeId +
                ", passOfficeName='" + passOfficeName + '\'' +
                '}';
    }
}
