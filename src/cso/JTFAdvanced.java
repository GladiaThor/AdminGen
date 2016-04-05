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
public class JTFAdvanced extends javax.swing.JTextField {

    String name;
    public String defText = "";
    Boolean mandatory;
    Color r = new Color(255, 153, 153);
    Color b = new Color(153, 153, 255);
    String pubName;

    /**
     * The PRIMARY constructor for an advanced field.
     *
     * @param defText default text
     * @param mandatory (true for making field mandatory) If left null can cause
     * error or unpredictable behavior.
     */
    public JTFAdvanced() {
    }

    public JTFAdvanced(String name, String defText, Boolean mandatory) {
        this.name = name;
        this.defText = defText;
        this.mandatory = mandatory;
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
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fromTheTop();
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

    /**
     * Moves the caret to the start of the field upon clicking an field with
     * default text. Goes with the focus GAINED action listeners
     */
    public void fromTheTop() {
        if (this.getText().equals(defText)) {
            this.setCaretPosition(0);
        } else;
    }



    /**
     * Forcefully resets the field to default text. Goes with the focus LOST
     * action listener
     */
    public void forceReset() {

        this.setText(defText);
        if (mandatory == true) {
            this.setForeground(r);
        } else {
            this.setForeground(b);
        }

    }

    /**
     * Setter for default text
     *
     * @param defText
     */
    public void setDefText(String defText) {
        this.defText = defText;
    }

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
