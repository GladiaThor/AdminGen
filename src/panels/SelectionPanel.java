/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Thor
 */
public class SelectionPanel extends JAPanel {

    String[] dataformats = {"asdp", "jpeg", "pdf", "txt"}; //Accepted dataformats by the boxes
    int leftIndex;
    int rightIndex;

    /**
     * Creates new form SelectionPanel
     */
    public SelectionPanel() {
        initComponents();
        try {
            setLeftBox("Templates");
            setRightBox("Students");
        } catch (Exception e) {

        }

    }

    public SelectionPanel(String left, String right, String focus) {
        initComponents();
        try{
        setLeftBox(left);
        setRightBox(right);
        }catch (Exception e){
            
        }
        leftBox.setSelectedItem(focus);
    }

    public void setLeftBox(String folder) throws Exception {
        LinkedList<File> j = (LinkedList<File>) FileUtils.listFiles(new File(folder), dataformats, false);
        String[] al = new String[j.size()];
        for (int i = 0; i < j.size(); i++) {
            String data = j.get(i).getName().toString(); //Get data
            for (int k = 0; k < dataformats.length; k++) {

                data = data.replace("." + dataformats[k], ""); //was the file ending
            }
            al[i] = data; //Add to array
            //al[i] = (j.get(i).getName().toString().replace(".pdf", ""));
        }
        leftBox.setModel(new javax.swing.DefaultComboBoxModel(al));
        leftBox.setSelectedIndex(leftIndex);
    }

    public void setRightBox(String folder) {

        LinkedList<File> j = (LinkedList<File>) FileUtils.listFiles(new File(folder), dataformats, false);
        String[] al = new String[j.size()];
        for (int i = 0; i < j.size(); i++) {
            String data = j.get(i).getName().toString(); //Get data
            for (int k = 0; k < dataformats.length; k++) {
                data = data.replace("." + dataformats[k], ""); //was the file ending
            }
            al[i] = (data); //Add to array
        }
        rightBox.setModel(new javax.swing.DefaultComboBoxModel(al)); //Add array as model for the combo box
    }

    /**
     * returns the value of the left box.
     *
     * @return
     */
    public String getLeftBox() {
        leftIndex = leftBox.getSelectedIndex();
        return leftBox.getSelectedItem().toString();

    }

    /**
     * returns the value of the right box.
     *
     * @return
     */
    public String getRightBox() {
        rightIndex = rightBox.getSelectedIndex();
        return rightBox.getSelectedItem().toString();
    }

    /**
     * Add action listener act to left combo box
     *
     * @param act
     */
    public void addActionToLeft(ActionListener act) {
        leftBox.addActionListener(act);
    }

    /**
     * Add action listener act to right combo box
     *
     * @param act
     */
    public void addActionToRight(ActionListener act) {
        leftBox.addActionListener(act);
    }

    /**
     * Sets value of right combo box to val.
     *
     * @param val
     */
    public void setLeftSelected(int val) {

        leftBox.setSelectedIndex(val);
    }

    /**
     * Sets value of right combo box to val.
     *
     * @param val
     */
    public void setRightSelected(String val) {
        rightBox.setSelectedItem(val);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftBox = new cso.AdvancedBox();
        rightBox = new cso.AdvancedBox();

        leftBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Äskan Mot Kvitto", "Studentärende" }));

        rightBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        rightBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rightBoxFocusGained(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(leftBox, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rightBox, 0, 194, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(leftBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(rightBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rightBoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rightBoxFocusGained
rightBox.showPopup();   // TODO add your handling code here:
    }//GEN-LAST:event_rightBoxFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox leftBox;
    public javax.swing.JComboBox rightBox;
    // End of variables declaration//GEN-END:variables
}
