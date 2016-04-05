/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cso;

import java.awt.Color;

/**
 *
 * @author Thor
 */
public class JPFAdvanced extends javax.swing.JPasswordField {

    public String defText = "";
    Boolean mandatory;
    Color r = new Color(255, 153, 153);
    Color b = new Color(153, 153, 255);

    public JPFAdvanced() {
        this.defText = "¤¤¤¤¤¤";
        this.mandatory = true;
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
        
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                reset();
            }
        });
        this.setText(defText);
    }


    /**
     * Call on key press to remove all text when default text showing.
     */
    public void clear() {
        if (this.getText().equals(defText)) {
            this.setForeground(Color.black);
            this.setText("");
        }

    }

    /**
     * Call on lost focus to reset to default text IF empty
     */
    public void reset() {
        if (this.getText().isEmpty()) {
            this.setText(defText);
            if (mandatory == true) {
                this.setForeground(r);
            } else {
                this.setForeground(b);
            }

        }

    }

    public void forceReset() {

        this.setText(defText);
        if (mandatory == true) {
            this.setForeground(r);
        } else {
            this.setForeground(b);
        }

    }



    public void setDefText(String defText) {
        this.defText = defText;
    }
}
