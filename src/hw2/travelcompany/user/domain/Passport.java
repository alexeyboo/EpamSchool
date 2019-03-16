package hw2.travelcompany.user.domain;

import java.util.Date;

public class Passport {
    private String serial;
    private String number;
    private Date dateOfIssue;

    public String getSerial() {
        return serial;
    }

    public String getNumber() {
        return number;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Passport(String serial, String number, Date dateOfIssue) {
        this.serial = serial;
        this.number = number;
        this.dateOfIssue = dateOfIssue;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "serial='" + serial + '\'' +
                ", number='" + number + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                '}';
    }
}
