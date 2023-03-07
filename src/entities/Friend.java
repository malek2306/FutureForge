/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Blob;

/**
 *
 * @author Name
 */
public  class Friend {
    private int id, valide;
    private String user1,user2;
    Blob pfp;

    public Friend() {
    }

    public Friend(String user1 , String user2,int valide) {
        this.user1 = user1;
        this.user2 = user2;
        this.valide = valide;
    }

    public Friend(int id,String user1 , String user2,int valide) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.valide = valide;
    }
    //getter
    public int getId() {
        return id;
    }

    public String getUser1() {
        return user1;
    }

    public String getUser2() {
        return user2;
    }
    
    public int getValide() {
        return valide;
    }
    
    
    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }
    
    public void setValide(int valide) {
        this.valide = valide;
    }

    @Override
    public String toString() {
        return "user{" + "user1=" + user1 + ", user2=" + user2 + ", valide=" + valide + "}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Friend other = (Friend) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
