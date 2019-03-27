package travelcompany.user.domain;

import java.util.Date;

public class Passport implements Comparable {
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

    @Override
    public int compareTo(Object o) {

        if (o instanceof Passport) {
            int intSerial = Integer.parseInt(serial);
            int oIntSerial = Integer.parseInt((((Passport) o).serial));
            int intNumber = Integer.parseInt(number);
            int oIntNumber = Integer.parseInt((((Passport) o).number));
            int outPut;
            if ((outPut = (intSerial - oIntSerial)) == 0) {
                return intNumber - oIntNumber;
            } else {
                return outPut;
            }
        }
        return -1;
    }
}
