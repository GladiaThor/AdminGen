/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cso;

import java.io.File;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import org.apache.commons.io.FileUtils;
import java.lang.String;

/**
 *
 * @author Thor
 */
public class AdvancedBox extends JComboBox {

    String type = "applicant";

    public AdvancedBox(DefaultComboBoxModel model) {
        this.setModel(model);
    }

    public AdvancedBox() {
    }

    public void setToApplicantBox() {
        String[] dataformats = {"asdp", "jpeg"};
        LinkedList<File> j = (LinkedList<File>) FileUtils.listFiles(new File("applicants"), dataformats, false);
        String[] al = new String[j.size()];
        for (int i = 0; i < j.size(); i++) {
            al[i] = (j.get(i).getName().toString().replace(".asdp", ""));
        }
        this.setModel(new javax.swing.DefaultComboBoxModel(al));
        type = "applicant";
    }
    public String itemiser(){
        return this.getSelectedItem().toString();
        
    }

    public void reInit() {
        if (type.equals("applicant")) {
            setToApplicantBox();
        }
    }
}
