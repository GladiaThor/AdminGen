/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;

/**
 *
 * @author ThoSal
 */
public class Organisation {

    String chairman = "NEW ORGANISATION";
    String name, email, orgNr;
    ArrayList<String> members;
    ArrayList<String> positions;

    Organisation(String name, String chairman) {

    }

    public void addMember(String name, String position) {
        members.add(name);
        positions.add(position);
    }

    public boolean removeMember(String name) {
        boolean found = false;
        for (int i = 0; i < members.size() && found == false; i++) {
            if (members.get(i).equals(name)) {
                members.remove(i);
                positions.remove(i);
                found = true;
            } else {

            }
        }
        return found;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChairman() {
        return chairman;
    }

    /**
     * Set chairman pNumb
     *
     * @param chairman
     */
    public void setChairman(String chairman) {
        if (chairman.equals("NEW ORGANISATION")) {
            this.chairman = chairman;
        } else {
            addMember(this.chairman, "memberXC" + Helpers.getDateYear());
        }
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
