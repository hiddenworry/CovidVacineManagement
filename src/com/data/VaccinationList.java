/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import uti.menu.Menu;
import util.InputHandler;

/**
 *
 * @author ADMIN
 */
public class VaccinationList extends ArrayList<Injection> {

    StudentList stList = new StudentList();
    VacineList VacList = new VacineList();
    final String DATABASE_DIR = System.getProperty("user.dir");
    // đường dẫn chính để lưu các file dữ liệu
    String Injectionfile_dir = DATABASE_DIR + "\\Database\\InjectionList\\Injection.dat";
// gọi contructor sẽ gọi hàm loadFromfile để add những đối tượng học sinh đã tim chủng trước đó vào danh sach 

    public VaccinationList() {
        loadFromFile(Injectionfile_dir);
    }

    // đường dẫn để add danh sách các sinh viên đã tiêm chủng vào list để tiện sử lý
    private void loadFromFile(String file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                Injection tmp = (Injection) ois.readObject();
                VaccinationList.super.add(tmp);
            }
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {

        }
    }
// được sử dụng ở hàm nhập để tránh duplicated, phục vụ cho add

    private String getInjectionID() {
        String ID;
        do {
            ID = InputHandler.getString("Enter injection ID: ", "Please make sure you injection id is not empty!!!", 10);
            if (searchInjectionByID(ID) == null) {
                return ID;
            } else {
                System.out.println("This Id already in the list, please try another");
            }
        } while (true);

    }

// hàm này có chức năng xử lý khi add một thông tin tiêm chủng mới, gọi hàm getStuDose() để lấy đước số muỗi tiêm
// của 1 đối tượng tương ứng được truyền vào    
    // có 3 trường hợp:
    // Sinh viên đã tiêm 0 mũi : thì gọi hàm  addInjection(Sid) để nhập thpngo tin tiêm chủng bình thường
    // sinh viên tiêm 1 mũi thì gọi hàm updateInjection(Injection I) để update thông tin tiêm muỗi 2
    // Sinh viên đã tiêm đủ 2 mũi thì sẽ hiển thông báo và thoát
    public void addStuInjection() {
        String Sid;
        Sid = stList.getStuID();
        Injection I = searchStuBySid(Sid);

        if (I == null) {
            addInjection(Sid);
        } else if (I.getDoses() == 1) {
            System.out.println("This student have been inject the first dose \n" + VacList.searchVacineByID(I.vID) + " at " + I.getD1());
            if (Menu.ContinueMessage("Do you want to add the second one")) {
                updateInjection(I);
            }
        } else {
            System.out.println("This student has had 2 injections");
        }

    }
// dùng để add thông tin tiêm chủng của một sinh viên có trong danh sách sinh viên

    private void addInjection(String Sid) {
        String ID, Vid, place1, d1, place2, d2;
        Injection I;
        ID = getInjectionID();
        Vid = VacList.getVacID();
        place1 = InputHandler.getString("Where was the first injection: ", "Sorry, make sure your input is not empty");
        d1 = InputHandler.checkDate1stDose("Enter the date of the 1st dose[dd/MM/yyyy]: ", "Invalid date, please input the a valid date(the date must after 2020)");
        if (Menu.ContinueMessage("Do you want to add the second injection?")) {
            place2 = InputHandler.getString("Where was the seconds dose: ", "Sorry, make sure your input is not empty");
            d2 = InputHandler.checkDate2ndDose(d1, "Enter the date of 2nd dose[dd/MM/yyyy]: ", "Invalid date, the 2nd dose must be inject after 4-12 week after the first one");
            I = new Injection(ID, Sid, Vid, place1, d1, place2, d2);
        } else {
            I = new Injection(ID, Sid, Vid, place1, d1);
        }
        VaccinationList.super.add(I);
        writetoFile(Injectionfile_dir);

    }

    // hàm này sẽ làm nhiệm vụ update thông tin tiêm mũi 2 của sinh viên
    public void updateInjection() {
        String Id = InputHandler.getString("Enter injection ID:", "please make sure you enter a valid ID", 10);
        Injection I = searchInjectionByID(Id);
        if (I != null) {
            System.out.println("Before update");
             System.out.printf("%5s|%15s|%15s|%30s|%30s|%30s|%30s|\n", "ID", "SID", "VID", "Date of first injection", "Place of 1st injection", "Date of second injection", "Place of 2nd injection");
            I.showInfor();
            System.out.println("Remember that you only permit to change the 2nd injection of the student");
            updateInjection(I);
            System.out.println("Update successfully");
            System.out.println("Student has completed 2 injections!");
            System.out.printf("%5s|%15s|%15s|%30s|%30s|%30s|%30s|\n", "ID", "SID", "VID", "Date of first injection", "Place of 1st injection", "Date of second injection", "Place of 2nd injection");
            I.showInfor();
            

        } else {
            System.out.println("Injection does not exist");
        }

    }

    private void updateInjection(Injection I) {
        String place2, date2;
        place2 = InputHandler.getString("Where was the seconds injection: ", "Sorry, make sure your input is not empty");
        date2 = InputHandler.checkDate2ndDose(I.d1, "Enter the date of 2nd dose: ", "Invalid date, the 2nd dose must be inject after 4-12 week after the first one");
        I.setPlace2(place2);
        I.setD2(date2);
        writetoFile(Injectionfile_dir);

    }

    private Injection searchInjectionByID(String id) {
        for (Injection Injected : this) {
            if (Injected.getID().equalsIgnoreCase(id)) {
                return Injected;
            }
        }
        return null;
    }
    // gọi hàm để thực hiện việc update thông tin muoix tiêm thứ 2

// hàm được dùng để tìm sinh viên có Sid trùng với đầu vào trong InjectionList(Danh sách đã tiêm chủng)
    private Injection searchStuBySid(String Sid) {
        for (Injection List : this) {
            if (List.getsID().equalsIgnoreCase(Sid)) {
                return List;
            }
        }
        return null;
    }
// hàm này có chức năng ghi các đối tượng trong InjectionList vào file
    public void saveToFile(){
        writetoFile(Injectionfile_dir);
        System.out.println(Injectionfile_dir);
    
    }
    private void writetoFile(String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Injection I : this) {
                oos.writeObject(I);
            }

            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Write failed");
        } catch (IOException ex) {

        }

    }

    public void showInjectedList() {
        System.out.printf("%5s|%15s|%15s|%30s|%30s|%30s|%30s|\n", "ID", "SID", "VID", "Date of first injection", "Place of 1st injection", "Date of second injection", "Place of 2nd injection");
        for (Injection Injected : this) {
            Injected.showInfor();
        }

    }

    public void deleteInjection() {
        String ID = InputHandler.getString("Enter injection ID:", "please make sure you enter a valid ID", 10);
        Injection I = searchInjectionByID(ID);
        if (I != null && Menu.ContinueMessage("Do you want to remove this Injection") == true) {
            System.out.println(I);
            VaccinationList.super.remove(I);
            System.out.println("Remove succesfully!!!!");
            writetoFile(Injectionfile_dir);
        } else if (I == null) {
            System.out.println("This ID is not in the InjectionList");
        } else {
            System.out.println("Remove failed");
        }

    }

    public void searchStuByID() {
        String Sid = InputHandler.getString("Enter the student id that you want to search", "Make sure you input is not empty");
        Injection I = searchStuBySid(Sid);
        if (I != null) {
            System.out.printf("%5s|%15s|%15s|%30s|%30s|%30s|%30s|\n", "ID", "SID", "VID", "Date of first injection", "Place of 1st injection", "Date of second injection", "Place of 2nd injection");
            I.showInfor();
        } else {
            System.out.println("This student is not in the Injection list");
        }

    }

}
