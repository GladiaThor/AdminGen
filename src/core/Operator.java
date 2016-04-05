/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.File;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Thor
 */
public class Operator {

    char[] password;
    String name, mail, tel, sektion, func; //Implimented
    String database; //To Be Implimented

    //TEMPORARY AS THE USERS WILL BE PICKED FROM A DATABASE LATER
   public  Operator(boolean b) {
        this.name = "%%&&%%&&##&&##&&";
    }
    //TEMPORARY AS THE USERS WILL BE PICKED FROM A DATABASE LATER
   public  Operator(String name, String phone, String sektion, String func) {
        this.name = name;
        this.tel = phone;
        this.sektion = sektion;
        this.func = func;
    }
//TEMPORARY AS THE USERS WILL BE PICKED FROM A DATABASE LATER

    public Operator(String source) {

        String user = "";
        try {
            user = FileUtils.readFileToString(new File("Operators\\" + source + ".asdp"));
        } catch (Exception e) {
            System.out.print(e);
        }
        String[] info = user.split("#");
        for (int i = 0; i < info.length; i++) {
            if (info[i].equals("oName")) {
                i++;
                setName(info[i]);
            } else if (info[i].equals("oFunc")) {
                i++;
                setFunc(info[i]);
            } else if (info[i].equals("oPhone")) {
                i++;
                setTel(info[i]);
            } else if (info[i].equals("oMail")) {
                i++;
                setMail(info[i]);
            } else if (info[i].equals("oSek")) {
                i++;
                setSektion(info[i]);
            } else {

            }
        }
    }

    /*

     name, func, tel, mail, clrnr, acnr, bank.
     */
    public ArrayList<String> compileValuesList() {
        ArrayList<String> valuesArrayList = new ArrayList();
        valuesArrayList.add(name);
        valuesArrayList.add(func);
        valuesArrayList.add(tel);
        valuesArrayList.add(mail);
        valuesArrayList.add(sektion);

        return valuesArrayList;
    }

    public ArrayList<String> compileFieldsList() {
        ArrayList<String> valuesArrayList = new ArrayList();
        valuesArrayList.add("oName");
        valuesArrayList.add("oFunc");
        valuesArrayList.add("oPhone");
        valuesArrayList.add("oMail");
        valuesArrayList.add("oSek");

        return valuesArrayList;
    }

    public void toFile() {
        String appWrite
                = "#oName#" + name
                + "#oFunc#" + func
                + "#oPhone#" + tel
                + "#oMail#" + mail
                + "#oSek#" + sektion;
        try {
            FileUtils.writeStringToFile(new File("Operators\\" + func + " _ " + name + ".asdp"), appWrite, "utf-8");
//        "
        } catch (Exception e) {

        }
    }

// <editor-fold defaultstate="collapsed" desc="Getters and setters">
    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String phone) {
        this.tel = phone;
    }

    public String getSektion() {
        return sektion;
    }

    public void setSektion(String sektion) {
        this.sektion = sektion;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }
    //</editor-fold>

}
