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
class Vaccine implements Serializable{
    private String Vid, name;

    public Vaccine(String Vid, String name) {
        this.Vid = Vid;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vaccine{" + "Vid=" + Vid + ", name=" + name + '}';
    }

   
    
    public String getID() {
        return Vid;
    }

    public void setID(String ID) {
        this.Vid = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
