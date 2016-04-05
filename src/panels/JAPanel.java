/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import core.Helpers;
import core.Operator;
import core.PDFField;
import core.Person;
import core.Populater;
import cso.JALabel;
import cso.JTAAdvanced;
import cso.JTFAdvanced;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Thor
 */
public class JAPanel extends JPanel {
// THIS ONLY EXISTS BECAUSE I AM TOO L>Y TO FIND A SOLUTION FOR MY SWAPING PANLES PROBLEM: ALL HAIL THE LAZYNESS OF THOR!

    JTFAdvanced pNumb, topic;

    public JAPanel() {

    }

    public JAPanel(String x) {

    }

    public String toPDF(String src) {
        return null;
    }

    public Person logIn() {
        return null;
    }

    public void epicAction(ActionListener o) {

    }

    public void autoFill(String itemiser) {

    }

    public void clearForm() {

    }

    public String createPDF(String source, String target) {
        source = "Templates\\" + source + ".PDF";
        String tTarget = "Prints\\" + target + ".pdf";
        Populater p = new Populater();
        try {
            p.createPDF(source, tTarget);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return tTarget;
    }

    String getLeftBox() {
        return null;
    }

    Person getPerson() {
        return null;
    }

    public ArrayList<PDFField> compileList() {
        java.awt.Component[] comp = Helpers.extractAll(this.getComponents());
        ArrayList<PDFField> retList = new ArrayList();
        for (int i = 0; i < comp.length; i++) {
            if (Helpers.compare(comp[i], new JTAAdvanced())) {
                JTAAdvanced x = (JTAAdvanced) comp[i];
                retList.add(x.value());
                //System.out.println("object added name :" + x.value().getName() + "Value:" + x.value().getValue());
            } else if (Helpers.compare(comp[i], new JTFAdvanced())) {
                JTFAdvanced x = (JTFAdvanced) comp[i];
                retList.add(x.value());
                // System.out.println("object added name :" + x.value().getName() + "Value:" + x.value().getValue());

            } else if (comp[i].getClass().equals(new JLabel().getClass())) {
                JLabel x = (JLabel) comp[i];
                retList.add(new PDFField(x.getName(), x.getText()));
                // System.out.println("object added name :" + x.getName() + "Value:" + x.getText());

            } else if (comp[i].getClass().equals(new JALabel().getClass())) {
                JALabel x = (JALabel) comp[i];
                retList.add(new PDFField(x.getName(), x.getText()));
                // System.out.println("object added name :" + x.getName() + "Value:" + x.getText());

            } else {
                // System.out.println("ELSE!");

            }
        }
        return retList;
    }
}
