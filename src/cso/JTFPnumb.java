/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cso;

/**
 *
 * @author Thor
 */
public class JTFPnumb extends JTFAdvanced {

    public JTFPnumb() {
        name = "pNumb";
        defText = "Personnummer";
        mandatory = true;
        this.setText(defText);
        if (mandatory == true) {
            this.setForeground(r);
        } else {
            this.setForeground(b);
        }
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {

                clear();
            }
        });

        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {

                corrector();
            }
        });

        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                reset();
            }
        });
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fromTheTop();
            }
        });

    }

    /**
     * Creates a valis personnummer (pNumb) fron given input
     *
     * @return
     */
    public void corrector() {

        String retString = ""; //Create a rutrun string
        char[] pNumb = this.getText().toCharArray(); //Convert the contecnt to a char string

        if (pNumb.length > 10 && pNumb[0] == '1' && pNumb[1] == '9') { //Remove 198 from 19YYMMDD-xxxx
            pNumb[0] = ' ';
            pNumb[1] = ' ';
        }

        for (int i = 0; i < pNumb.length && i < 11; i++) { //Create string from the digits in the char array
            if (Character.isDigit(pNumb[i])) {
                retString = retString + pNumb[i];
            }

        }

        this.setText(retString); //sets teh text to the correct string corrected string
    }

}
