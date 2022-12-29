package edu.petproject.statement.domain;

public class EducationOffice {
    private int eduOfficeId;
    private String eduOfficeName;

    public EducationOffice() {
    }

    public EducationOffice(int eduOfficeId, String eduOfficeName) {
        this.eduOfficeId = eduOfficeId;
        this.eduOfficeName = eduOfficeName;
    }

    public int getEduOfficeId() {
        return eduOfficeId;
    }

    public void setEduOfficeId(int eduOfficeId) {
        this.eduOfficeId = eduOfficeId;
    }

    public String getEduOfficeName() {
        return eduOfficeName;
    }

    public void setEduOfficeName(String eduOfficeName) {
        this.eduOfficeName = eduOfficeName;
    }

    @Override
    public String toString() {
        return "EducationOffice{" +
                "eduOfficeId=" + eduOfficeId +
                ", eduOfficeName='" + eduOfficeName + '\'' +
                '}';
    }
}
