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
class Injection implements Serializable {

    StudentList stu = new StudentList();
    String ID, vID, sID, sName;
    String place1;
    String d1;
    String place2;
    String d2;

    public Injection(String ID, String sID, String vID, String place1, String d1, String place2, String d2) {
        this.ID = ID;
        this.vID = vID;
        this.sID = sID;
        this.sName = stu.getNamebyID(sID);
        this.place1 = place1;
        this.d1 = d1;
        this.place2 = place2;
        this.d2 = d2;
    }

    public Injection(String ID, String sID, String vID, String place1, String d1) {
        this.ID = ID;
        this.vID = vID;
        this.sID = sID;
        this.sName = stu.getNamebyID(sID);
        this.place1 = place1;
        this.d1 = d1;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getvID() {
        return vID;
    }

    public void setvID(String vID) {
        this.vID = vID;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getPlace2() {
        return place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    @Override
    public String toString() {
        return "Injection{ ID=" + ID + ", vID=" + vID + ", sID=" + sID + ", sName=" + sName + ", place1=" + place1 + ", d1=" + d1 + ", place2=" + place2 + ", d2=" + d2 + '}';
    }

    public void showInfor() {

        System.out.printf("%5s|%25s|%30s|%15s|%30s|%30s|%30s|%30s|\n", ID, sID, sName, vID, d1, place1, d2, place2);

    }

    // hàm này trả về số mũi tiêm của Sinh Viên trong đối tượng injection
    int getDoses() {
        if (this.getD2() == null) {
            return 1;
        } else {
            return 2;
        }
    }

}
