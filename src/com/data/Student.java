/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
 class Student implements Serializable{
     protected String sID, sName;

    public Student(String ID, String name) {
        this.sID = ID;
        this.sName = name;
    }

    public Student(String sID) {
        this.sID = sID;
    }
    
   
    

    @Override
    public String toString() {
        return sID + ", " + sName;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getsName() {
        
        return sName;
    }
   

    public void setsName(String sName) {
        this.sName = sName;
    }
    
}
