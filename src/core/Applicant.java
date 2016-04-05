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
public class Applicant {

    String comp, name, func, tel, mail, bank, clrnr, acnr;
    String na = "N/A";
    String[] fillerArray = {"Sökande",
        "Sökt Belopp",
        "Namn",
        "Funktion hos sökande", "Telefonnr.",
        "E-post",
        "Clearingnr.",
        "Kontonr/postgiro/bankgiro",
        "Utfärdande bank",};

    /**
     * Constructs an applicant with Name, tel and mail (DEFAULT)
     *
     * @param name
     * @param tel
     * @param mail
     */
    public Applicant(String name, String tel, String mail) {
        setName(name);
        setComp(na);
        setFunc(na);
        setTel(tel);
        setMail(mail);
        setBank(na);
        setClrnr(na);
        setAcnr(na);
    }

    /**
     * Constructs an applicant object from file
     *
     * @param filepath
     */
    public Applicant(String fileName) {
        String user = "";
        try {
            user = FileUtils.readFileToString(new File("applicants\\"+fileName+".asdp"));
        } catch (Exception e) {

        }
        String[] info = user.split("#");
        for(int i = 0; i<info.length; i++){
            if(info[i].equals("sgName")) {
                i++;
                setComp(info[i]);   
            } else if(info[i].equals("pName")) {
                i++;
                setName(info[i]);   
            } else if(info[i].equals("pPosition")) {
                i++;
                setFunc(info[i]);   
            } else if(info[i].equals("pPhone")) {
                i++;
                setTel(info[i]);   
            } else if(info[i].equals("pMail")) {
                i++;
                setMail(info[i]);   
            } else if(info[i].equals("pBank")) {
                i++;
                setBank(info[i]);   
            } else if(info[i].equals("pKonto")) {
                i++;
                setClrnr(info[i].split("THISISADELIMITERTROLOLOLOLOLOL")[0]);   
                setAcnr(info[i].split("THISISADELIMITERTROLOLOLOLOLOL")[1]);   
            } else {
                
            }
        }
        
    }

    public String whoIs() {
        return name + " is the " + func + " at " + comp + " and can be reached by phone at: " + tel + " or by email at: " + mail + ".";

    }

    public void saveToFile() {
        String appWrite
                = "#sgName#" + comp
                + "#pName#" + name
                + "#pPosition#" + func
                + "#pPhone#" + tel
                + "#pMail#" + mail
                + "#pBank#" + bank
                + "#pKonto#" + clrnr + "THISISADELIMITERTROLOLOLOLOLOL" + acnr;
        try {
            FileUtils.writeStringToFile(new File("applicants\\"+ comp +" - "+ name+".asdp"), appWrite, "utf-8");
//        "
        } catch (Exception e) {

        }
    }

    public String getComp() {
        return comp;
    }
// <editor-fold defaultstate="collapsed" desc="Getters and setters">

    public void setComp(String comp) {
        if (comp.isEmpty() || Helpers.isValInAr(comp, fillerArray)) {
            this.comp = na;
        } else {
            this.comp = comp;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty() || Helpers.isValInAr(name, fillerArray)) {
            this.name = na;
        } else {
            this.name = name;
        }
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        if (func.isEmpty() || Helpers.isValInAr(func, fillerArray)) {
            this.func = na;
        } else {
            this.func = func;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        if (bank.isEmpty() || Helpers.isValInAr(bank, fillerArray)) {
            this.bank = na;
        } else {
            this.bank = bank;
        }
    }

    public String getClrnr() {
        return clrnr;
    }

    public void setClrnr(String clrnr) {
        if (clrnr.isEmpty() || Helpers.isValInAr(clrnr, fillerArray)) {
            this.clrnr = na;
        } else {
            this.clrnr = clrnr;
        }
    }

    public String getAcnr() {
        return acnr;
    }

    public void setAcnr(String acnr) {
        if (acnr.isEmpty() || Helpers.isValInAr(acnr, fillerArray)) {
            this.acnr = na;
        } else {
            this.acnr = acnr;
        }
    }

// </editor-fold>
    /*

     name, func, tel, mail, clrnr, acnr, bank.
     */
    public ArrayList<String> compileValuesList() {
        ArrayList<String> valuesArrayList = new ArrayList();
        valuesArrayList.add(name);
        valuesArrayList.add(name);
        valuesArrayList.add(func);
        valuesArrayList.add(tel);
        valuesArrayList.add(mail);
        valuesArrayList.add(clrnr + " " + acnr);
        valuesArrayList.add(bank);
        valuesArrayList.add(comp);

        return valuesArrayList;
    }

    public ArrayList<String> compileFieldsList() {
        ArrayList<String> valuesArrayList = new ArrayList();
        valuesArrayList.add("pName");
        valuesArrayList.add("pSigName");
        valuesArrayList.add("pPosition");
        valuesArrayList.add("pPhone");
        valuesArrayList.add("pMail");
        valuesArrayList.add("pKonto");
        valuesArrayList.add("pBank");
        valuesArrayList.add("sgName");

        return valuesArrayList;
    }

}
