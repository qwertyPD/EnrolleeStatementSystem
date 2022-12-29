package edu.petproject.statement.domain;

public abstract class Document {
    private String surname;
    private String name;
    private String patronymic;
    protected int seria;
    protected int number;
    protected String date;

    public Document() {
    }

    public Document(String surname, String name, String patronymic, int seria, int number, String date) {
        this.seria = seria;
        this.number = number;
        this.date = date;
    }

    public int getSeria() {
        return this.seria;
    }

    public void setSeria(int seria) {
        this.seria = seria;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
