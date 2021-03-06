/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import cso.JTAAdvanced;
import cso.JTFAdvanced;
import java.awt.Component;
import java.util.ArrayList;

/**
 *
 * @author Thor
 */
public class ErrandFormPanel extends panels.JAPanel {

    /**
     * Creates new form ErrandNewPanel
     */
    public ErrandFormPanel() {

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pName = new JTFAdvanced("pName", "För- Efternamn", true);
        pFunc = new JTFAdvanced("pFunc","Position", true);
        pNumb = new JTFAdvanced("pNumb","Personnummer (YYMMDD-NNNN)", true)
        ;
        pMail = new JTFAdvanced("pMail","E-Post", true);
        pTel = new JTFAdvanced("pPhone","Telefonnummer", true);
        anonCB = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fak = new JTFAdvanced("fak","Fakultet/Sektion",true);
        inst = new JTFAdvanced("inst" ,"Institution/Kårorganisation", false);
        course = new JTFAdvanced("course","Kurskod", false);
        topic = new JTFAdvanced("topic","Område", true);
        jScrollPane1 = new javax.swing.JScrollPane();
        desc = new JTAAdvanced("desc","Förklara de problem som behöver utredas", true);
        jLabel3 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(386, 300));
        setMinimumSize(new java.awt.Dimension(386, 300));
        setPreferredSize(new java.awt.Dimension(386, 300));

        pName.setText("För- Efternamn");

        pFunc.setText("Position");

        pNumb.setText("Personnummer (YYMMDD-NNNN)");

        pMail.setText("E-Post");

        pTel.setText("Telefonnummer");

        anonCB.setText("Anonymt");
        anonCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anonCBActionPerformed(evt);
            }
        });

        jLabel1.setText("Kontaktuppgifter");

        jLabel2.setText("Ärendet berör:");

        fak.setText("Fakultet/Sektion");

        inst.setText("Institution/Kårorganisation");

        course.setText("Kurskod");

        topic.setText("Område");

        desc.setColumns(20);
        desc.setRows(5);
        jScrollPane1.setViewportView(desc);

        jLabel3.setText("Beskrivning");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(anonCB))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(course, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(fak, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pMail)
                            .addComponent(pFunc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pNumb)
                            .addComponent(pTel)
                            .addComponent(inst)
                            .addComponent(topic))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anonCB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pNumb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(topic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void anonCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anonCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anonCBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox anonCB;
    private javax.swing.JTextField course;
    private javax.swing.JTextArea desc;
    private javax.swing.JTextField fak;
    private javax.swing.JTextField inst;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pFunc;
    private javax.swing.JTextField pMail;
    private javax.swing.JTextField pName;
    private javax.swing.JTextField pNumb;
    private javax.swing.JTextField pTel;
    private javax.swing.JTextField topic;
    // End of variables declaration//GEN-END:variables

    public void autoFill(String itemiser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void clearForm() {
        Component[] comp = this.getComponents();
        ArrayList<JTFAdvanced> jTFAr = new ArrayList();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i].getClass().equals(new JTFAdvanced().getClass())) {
                jTFAr.add((JTFAdvanced) comp[i]);
            }
        }
        for (int i = 0; i < jTFAr.size(); i++) {
            jTFAr.get(i).forceReset();
        }
    }

    /**
     * Completes the list of value names
     *
     * @return
     */
    public ArrayList<String> completeValue(ArrayList<String> values) {
        values.add(fak.getText());
        values.add(inst.getText());
        values.add(course.getText());
        values.add(topic.getText());
        values.add(desc.getText());
        values.add(pFunc.getText());
        return values;
    }

    /**
     * completes the list of field names
     *
     * @return
     */
    public ArrayList<String> completeNames(ArrayList<String> names) {
        names.add("fak");
        names.add("inst");
        names.add("course");
        names.add("topic");
        names.add("desc");
        names.add("pFunc");
        return names;
    }


}
