package edu.petproject.statement.domain;

public class Passport extends Document {
    private PassportOffice passportOffice;

    public Passport() {
    }

    public Passport(String surname, String name, String patronymic, int seria, int number, String date) {
        super(surname, name, patronymic, seria, number, date);
    }

    public PassportOffice getPassportOffice() {
        return passportOffice;
    }

    public void setPassportOffice(PassportOffice passportOffice) {
        this.passportOffice = passportOffice;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "passportOffice=" + passportOffice +
                ", seria=" + seria +
                ", number=" + number +
                ", date='" + date + '\'' +
                "} " + super.toString();
    }
}
