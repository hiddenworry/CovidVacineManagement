/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;


import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import util.InputHandler;

/**
 *
 * @author ADMIN
 */
 class VacineList extends ArrayList<Vaccine> {

    final String dir = System.getProperty("user.dir") + "\\Database\\VacineList\\VacList.dat";

    public VacineList() {
        addData();
    }

    // đường dẫn lưu file StList.dat có sẵn
    Vaccine searchVacineByID(String ID) {
        for (Vaccine Vac : this) {
            if(Vac.getID().equalsIgnoreCase(ID))
                return Vac;
        }
        return null;

    }
    public void printVacineList(){
        for (Vaccine Vac : this) {
            System.out.printf("\nVaccine: %15s|   VID: %8s", Vac.getName(), Vac.getID());
        }
        System.out.println();// xuống dòng
    
    }
   
    private void addData() {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(dir);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            VacineList.super.add(new Vaccine("Covid-V001", "AstraZeneca"));
            VacineList.super.add(new Vaccine("Covid-V002", "Sputnik V"));
            VacineList.super.add(new Vaccine("Covid-V003", "Vero Cell"));
            VacineList.super.add(new Vaccine("Covid-V004", "Pfizer"));
            VacineList.super.add(new Vaccine("Covid-V005", "Moderna"));
            for (Vaccine VacList : this) {
                oos.writeObject(VacList);
            }
            oos.close();
            fos.close();

        } catch (Exception e) {
           

        }
    }
    
    // hàm này có chức năng sẽ xác nhận xem danh Vid có tồn tại hay không
    // đồng thời sẽ in ra một danh sách vaccine để người dùng lựa chọn
     String getVacID() {
        String Vid;
        this.printVacineList();
        do {
            Vid = InputHandler.getString("Enter Vacine ID[Example covid-v001]:", "Sorry, make sure your input is not empty");
            if (this.searchVacineByID(Vid) != null) {
                return Vid;
            } else {
                System.out.println("This Vacine is not in the list!!!");
            }

        } while (true);
     }

}
