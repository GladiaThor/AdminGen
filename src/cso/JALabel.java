/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cso;

import javax.swing.JLabel;

/**
 *
 * @author Thor
 */
public class JALabel extends JLabel {

    String name;

    public JALabel() {

    }

    public JALabel(String name, String content) {
        this.setText(content);
        this.name = name;
    }
    


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
