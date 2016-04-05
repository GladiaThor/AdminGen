/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cso;

import core.PDFField;
import java.awt.Color;

/**
 *
 * @author Thor
 */
public class JTAAdvanced extends javax.swing.JTextArea {

    String name;
    public String defText = "";
    Boolean mandatory;
    Color r = new Color(255, 153, 153);
    Color b = new Color(153, 153, 255);

    public JTAAdvanced(String name, String defText, Boolean mandatory) {
        this.defText = defText;
        this.mandatory = mandatory;
        this.name = name;
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

    public JTAAdvanced() {

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

    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns a PDFField object with the name of the field and the text. Sexy
     * AS FUCK!
     *
     * @return
     */
    public PDFField value() {
        return new PDFField(getName(), this.getText());
    }
}
