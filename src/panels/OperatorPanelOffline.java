/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import core.DatabaseHandler;
import core.Person;
import cso.JPFAdvanced;
import cso.JTFAdvanced;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 *
 * @author Thor
 */
public class OperatorPanelOffline extends panels.JAPanel {

    ActionListener epicness;

    /**
     * Creates new form OperatorPanelOffline
     */
    public OperatorPanelOffline() {
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

        userTF = new JTFAdvanced("", "Användarnamn", true);
        passTF = new JPFAdvanced();
        jLabel1 = new javax.swing.JLabel();
        logBut = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(400, 93));
        setMinimumSize(new java.awt.Dimension(400, 93));
        setPreferredSize(new java.awt.Dimension(400, 93));

        passTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passTFActionPerformed(evt);
            }
        });

        jLabel1.setText("__________________________________________________________________");

        logBut.setText("Logga in");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userTF, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passTF, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(logBut)))
                .addContainerGap(4, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(userTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logBut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void passTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passTFActionPerformed

    public Person logIn() {
        String name = userTF.getText().trim();
        char[] pass = passTF.getPassword();

        if (verify(name, pass).contains("true")) {
            try {
                System.out.println("PASSSSSS: Password verified!");
                return new Person(name);
            } catch (Exception e) {
                e.printStackTrace();
                return new Person();
            }

        } else {
            return new Person();
        }

    }

    private static String verify(String name, char[] pass) {
        boolean isCorrect = true;
        String error = "";
        char[] correctPassword = "1".toCharArray();
        try {
            DatabaseHandler dB = new DatabaseHandler("DBSettings");
            System.out.println("getting pass");
            correctPassword = dB.query("SELECT `password` FROM `gota`.`person`  WHERE numb='" + name + "';").get(0).trim().toCharArray();
            //correctPassword = FileUtils.readFileToString(new File("Operators\\" + name + ".asdp")).split("#")[0].replace(" ", "").toCharArray();
            System.out.println("Checkign pass");

        } catch (Exception e) {

            isCorrect = false;
        }
        try {
            for (int i = 0; i < pass.length; i++) {
                if (pass[i] != correctPassword[i]) {
                    isCorrect = false;

                } else {
                }
            }
        } catch (Exception e) {
            isCorrect = false;
        }

        //Zero out the password.
        Arrays.fill(correctPassword, '0');
        return isCorrect + error;
    }

    private void getPass(String source) {

    }

    @Override
    public void epicAction(ActionListener act) {
        epicness = act;
        logBut.addActionListener(epicness);
        passTF.addActionListener(epicness);

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logBut;
    private javax.swing.JPasswordField passTF;
    private javax.swing.JTextField userTF;
    // End of variables declaration//GEN-END:variables
}
