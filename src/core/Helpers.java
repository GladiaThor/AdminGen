/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import cso.JALabel;
import cso.JTAAdvanced;
import cso.JTFAdvanced;
import java.awt.Component;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import javax.swing.JLabel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Thor
 */
public class Helpers {

    public static boolean isValInAr(String val, String[] ar) {

        //System.out.print(arrayToList(ar).contains(val));
        return arrayToList(ar).contains(val);
    }

    public static ArrayList arrayToList(Object[] array) {
        ArrayList returnAL = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            returnAL.add(array[i]);
        }
        return returnAL;
    }

    public String[] getApplicants() {
        String[] dataformats = {"asdp", "jpeg"};
        LinkedList<File> j = (LinkedList<File>) FileUtils.listFiles(new File("applicants"), dataformats, false);
        String[] al = new String[j.size()];
        for (int i = 0; i < j.size(); i++) {
            al[i] = (j.get(i).getName().toString().replace(".asdp", ""));
        }

        return al;
    }

    public String getDate() {
        return new SimpleDateFormat("yyyy - MM - dd").format(Calendar.getInstance().getTime());
    }

    public static String getDateDay() {
        return new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
    }

    public static String getDateMonth() {
        return new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
    }

    public static String getDateYear() {
        return new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
    }

    /**
     * will return true if x is of teh same class as y
     *
     * @param x
     * @param y
     * @return
     */
    public static boolean compare(Object x, Object y) {
        if (x.getClass().equals(y.getClass())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Extracts all JLabel, JTFAdvanced and JTAAdvanced from a Component[] and
     * all of its sub containers.
     *
     * @param inputList
     * @return
     */
    public static Component[] extractAll(Component[] inputList) {
        ArrayList<Component> retVal = new ArrayList();
        for (int i = 0; i < inputList.length; i++) {
            if (compare(inputList[i], new JLabel()) || compare(inputList[i], new cso.JTFAdvanced()) || compare(inputList[i], new JTAAdvanced()) || compare(inputList[i], new JALabel())) {
                //Check if the type fitts the list above if right add it ro rreturn lisst
                retVal.add(inputList[i]);
            } else {
                try {
                    Container c = (Container) inputList[i]; //Typecast to container (If it fails the try will discard it)
                    Component[] inner = extractAll(c.getComponents()); //Recursive call for getting all components from container
                    for (int k = 0; k < inner.length; k++) {
                        retVal.add(inner[k]); //Add all componenets to return arraylsit
                    }
                } catch (Exception e) {

                }

            }

        }
        //Convert arraylist to array of type Component
        Component[] retArr = new Component[retVal.size()];
        for (int i = 0; i < retVal.size(); i++) {
            retArr[i] = retVal.get(i);
        }
        return retArr; //Be happy and love Java. #SomeMetaShitRightHere #NextLevelCoding "YOLO
    }

    public static void membersToDB(String fileName) {
        ArrayList<Person> per = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(new File("Students\\" + fileName + ".asdp")))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parsed = line.split(",");
                if (!parsed[0].equals("Förnamn")) {
                    per.add(new Person(parsed[0], parsed[1], parsed[2], "Thor@Asgard.pm", parsed[4], fileName));
                }
            }
        } catch (Exception e) {

        }
        for (int i = 0; i < per.size(); i++) {
            try {
                per.get(i).toDB();
            } catch (IOException e) {

            }
        }

    }
}
