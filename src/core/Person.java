/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Thor
 */
public class Person {

    String password;
    boolean op = false, obs = false;

    String firstName, surName, tel, mail, numb, clrNr, accNr, bank, sek, pos, zip, address;

    String na = "N/A";
    String[] fillerArray = {"För- Efternamn",
        "Position",
        "Personnummer (YYMMDD-NNNN)",
        "Telefonnummer",
        "E-post", "Kontonr/postgiro/bankgiro", "Clearingnr.", "Utfärdande bank"};

    public Person(String numb) throws IOException {

        DatabaseHandler dB = new DatabaseHandler("DBSettings");
        ArrayList<String> values = dB.query("SELECT * FROM `gota`.`person`  WHERE numb='" + numb + "';");
        for (int i = 0; i < values.size(); i++) {
            System.out.println(i + " ::" + values.get(i));
        }
        tel = values.get(0);
        mail = values.get(1);
        firstName = values.get(2);
        setNumb(values.get(3));
        setClrNr(values.get(4));
        setAccNr(values.get(5));
        setBank(values.get(6));
        setOp(values.get(7));
        setObs(values.get(8));
        setSurName(values.get(10));
        setSek(values.get(12));
        setPos(values.get(13));
        this.zip = values.get(14);
        this.address = values.get(15);

    }

    /**
     * Life is empty with an empty person. Have fun <3
     */
    public Person() {
        setNumb("%%&&%%&&##&&##&&");
    }

    /**
     * Constructs an applicant with Name, tel, mail and personal number (DEFAULT
     * FOR STUDENT MASS WRITING DO NOT USE FOR OTHER PURPOSES)
     *
     * @param firstName
     * @param zip
     * @param mail
     * @param numb
     */
    public Person(String firstName, String surName, String numb, String mail, String zip, String sek) {
        this.firstName = firstName;
        this.surName = surName;
        this.numb = numb;
        this.zip = zip;
        this.mail = mail;
        this.op = false;
        this.obs = false;
        this.sek = sek;
        this.pos = "student";
        this.tel = null;
        this.clrNr = null;
        this.accNr = null;
        this.bank = null;
        this.address = null;

    }

    /**
     * Constructs an applicant object from file (Depricated use database
     * connection <3)
     *
     * @param filepath
     */
    public Person(String fileName, boolean isFile) {

    }

    public boolean toBoolean(String input) {
        try {
            if (Integer.parseInt(input) == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;

        }
    }

    public String whoIs() {
        return firstName + " can be reached by phone at: " + tel + " or by email at: " + mail + ".";

    }

    public void saveToFile() {
        String appWrite
                = "#pNumb#" + numb
                + "#pName#" + firstName
                + "#pPhone#" + tel
                + "#pMail#" + mail;
        try {
            FileUtils.writeStringToFile(new File("applicants\\" + numb + " - " + firstName + ".asdp"), appWrite, "utf-8");
//        "
        } catch (Exception e) {

        }
    }

    /**
     * Checks input is a pNumber. (contains - and is 11 digits long);
     *
     * @param input
     * @return
     */
    public boolean ispNumb(String input) {

        if (input.contains("-") && input.length() == 11) {
            return true;
        } else if (input.contains("-") && input.length() == 11) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if input is a phone number. (starts with +46 and is all digits)
     *
     * @param input
     * @return
     */
    public boolean isPhone(String input) {
        if (input.startsWith("+46")) {
            try {
                Integer.parseInt(input.replace("+", ""));
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * Checks if input is an email address (contains @ sign and at least one .)
     *
     * @param input
     * @return
     */
    public boolean isMail(String input) {
        if (input.contains("@") && input.contains(".")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean toDB() throws IOException {
        DatabaseHandler db = new DatabaseHandler("DBSettings");
        String fields = "INSERT INTO `gota`.`person` (`tel`, `mail`, `firstName`, `numb`, `clrNr`, `accNr`, `bank`, `op`, `obs`, `password`, `surName`, `Sektion`, `Position`)";
        String values = "VALUES ('" + tel + "', '" + mail + "', '" + firstName + "', '" + numb + "', '" + clrNr + "', '" + accNr + "', '" + bank + "', " + op + ", " + obs + ", '" + password + "', '" + surName + "', '" + sek + "', '" + pos + "');";
        db.insertHandler(fields + values);
        return true;
    }
// <editor-fold defaultstate="collapsed" desc="Getters and setters">

    public String getSek() {
        return sek;
    }

    public void setSek(String sek) {
        this.sek = sek;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String func) {
        this.pos = func;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = toBoolean(op);
    }

    public boolean isObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = toBoolean(obs);
    }

    public String getClrNr() {
        return clrNr;
    }

    public void setClrNr(String clrNr) {
        try {
            if (clrNr.isEmpty() || Helpers.isValInAr(clrNr, fillerArray)) {
                this.clrNr = na;
            } else {
                this.clrNr = clrNr;
            }
        } catch (NullPointerException e) {
            this.clrNr = na;
        }
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getAccNr() {
        return accNr;
    }

    public void setAccNr(String accNr) {
        try {
            if (accNr.isEmpty() || Helpers.isValInAr(accNr, fillerArray)) {
                this.accNr = na;
            } else {
                this.accNr = accNr;
            }
        } catch (NullPointerException e) {
            this.accNr = na;
        }
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        try {
            if (bank.isEmpty() || Helpers.isValInAr(bank, fillerArray)) {
                this.bank = na;
            } else {
                this.bank = bank;
            }
        } catch (NullPointerException e) {
            this.bank = na;
        }
    }

    public String getNumb() {
        return numb;
    }

    public void setNumb(String numb) {
        if (numb.isEmpty() || Helpers.isValInAr(numb, fillerArray)) {
            this.numb = na;
        } else {
            this.numb = numb;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        if (name.isEmpty() || Helpers.isValInAr(name, fillerArray)) {
            this.firstName = na;
        } else {
            this.firstName = name;
        }
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        if (tel.isEmpty() || Helpers.isValInAr(tel, fillerArray)) {
            this.tel = na;
        } else {
            this.tel = tel;
        }
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        if (mail.isEmpty() || Helpers.isValInAr(mail, fillerArray)) {
            this.mail = na;
        } else {
            this.mail = mail;
        }
    }

// </editor-fold>
}
