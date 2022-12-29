package edu.petproject.statement.domain;

public class Diploma extends Document {
    private EducationOffice educationOffice;

    public Diploma() {
    }

    public Diploma(String surname, String name, String patronymic, int seria, int number, String date) {
        super(surname, name, patronymic, seria, number, date);
    }

    public EducationOffice getEducationOffice() {
        return this.educationOffice;
    }

    public void setEducationOffice(EducationOffice educationOffice) {
        this.educationOffice = educationOffice;
    }

    @Override
    public String toString() {
        return "Diploma{" +
                "educationOffice=" + educationOffice +
                ", seria=" + seria +
                ", number=" + number +
                ", date='" + date + '\'' +
                "} " + super.toString();
    }
}
