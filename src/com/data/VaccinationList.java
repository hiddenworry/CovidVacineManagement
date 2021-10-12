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
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import uti.menu.Menu;
import util.InputHandler;
import util.MD5Encrytion;

/**
 *
 * @author ADMIN
 */
public class VaccinationList extends ArrayList<Injection> {

    private final String DATABASE_DIR = System.getProperty("user.dir");
    private final String Injectionfile_dir = DATABASE_DIR + "\\Database\\InjectionList\\Injection.dat";
    StudentList StList = new StudentList();
    VacineList VacList = new VacineList();

    public VaccinationList() {
        loadFromFile();
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = new FileInputStream(Injectionfile_dir);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                VaccinationList.super.add((Injection) ois.readObject());

            }
            fis.close();
            ois.close();
        } catch (Exception e) {

        }

    }

    private String getInjection() {
        Injection I;
        String ID;
        do {
            ID = InputHandler.getString("Enter Injection ID: ", "Please make sure your input is not empty");
            I = searchInjectionByID(ID);
            if (I == null) {
                return ID;
            } else {
                System.out.println("This InJection has been created before!!!");
            }

        } while (true);

    }

    public void addStuInjection() {
        String Sid = StList.getStuID();
        Injection I = searchStuBySid(Sid);
        if (I == null) {
            addInjection(Sid);
        } else if (I != null && I.getDoses() == 1) {
            System.out.println("this student has had 1 injection of" + I.toString());
            if (Menu.ContinueMessage(" Do you want ot continues input the 2nd one")) {
                updateInjection(I);
            }

        } else {
            System.out.println("This student has had 2 injections");
        }

        writetoFile(Injectionfile_dir);
    }

    private void addInjection(String Sid) {
        String ID, Vid, place1, date1, place2, date2;
        ID = getInjection();
        Vid = VacList.getVacID();
        place1 = InputHandler.getString("Enter the first places of 1st Injection", "Make sure your input is not empty");
        date1 = InputHandler.checkDate1stDose("Enter the date of 1st dose:", "Make sure you enter corect date(must after 2019)");
        if (Menu.ContinueMessage("Do you want to input the 2nd injection inform")) {
            place2 = InputHandler.getString("Enter the first places of 2nd Injection", "Make sure your input is not empty");
            date2 = InputHandler.checkDate2ndDose(date1, "Enter the first places of 2nd Injection", "please make sure the 2nd dose after first 4-12 week!!");
            VaccinationList.super.add(new Injection(ID, Sid, Vid, place1, date1, place2, date2));
            writetoFile(Injectionfile_dir);
        } else {
            VaccinationList.super.add(new Injection(ID, Sid, Vid, place1, date1));
            writetoFile(Injectionfile_dir);

        }

    }

    // hàm này sẽ làm nhiệm vụ update thông tin tiêm mũi 2 của sinh viên
    public void updateInjection() {
        String Id = InputHandler.getString("Enter injection ID:", "please make sure you enter a valid ID", 10);
        Injection I = searchInjectionByID(Id);
        if (I != null) {
            System.out.println("Before update");
            System.out.printf("%5s|%25s|%30s|%15s|%30s|%30s|%30s|%30s|\n", "ID", "SID", "SName", "VID", "Date of first injection", "Place of 1st injection", "Date of second injection", "Place of 2nd injection");
            I.showInfor();
            System.out.println("Remember that you only permit to change the 2nd injection of the student");
            updateInjection(I);
            System.out.println("Update successfully");
            System.out.println("Student has completed 2 injections!");
            System.out.printf("%5s|%15s|%40s|%15s|%30s|%30s|%30s|%30s|\n", "ID", "SID", "SName", "VID", "Date of first injection", "Place of 1st injection", "Date of second injection", "Place of 2nd injection");
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

    public void saveToFile() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        writetoFile(Injectionfile_dir);
        System.out.println(Injectionfile_dir);

    }

    public void encrypt() throws NoSuchAlgorithmException {

        String Encrypt_dir = DATABASE_DIR + "\\Database\\InjectionList\\Encrypt\\InjectEncrypted.dat";
        try {
            FileOutputStream fos = new FileOutputStream(Encrypt_dir);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Injection I : this) {
                oos.writeObject(MD5Encrytion.encrypt(I));
            }

            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Write failed");
        } catch (IOException ex) {
            System.out.println("Write failed");
        }

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
            System.out.println("Write failed");
        }

    }

    public void showInjectedList() {

        printInjectionList(this);

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
            System.out.println("Injection does not exist");
        } else {
            System.out.println("Remove failed");
        }

    }

    public void searchInjectedStuBySid() {
        String Sid = InputHandler.getString("Enter the student id that you want to search", "Make sure you input is not empty");
        Injection I = searchStuBySid(Sid);
        if (I != null) {
            System.out.printf("%5s|%25s|%30s|%15s|%30s|%30s|%30s|%30s|\n", "ID", "SID", "SName", "VID", "Date of first injection", "Place of 1st injection", "Date of second injection", "Place of 2nd injection");
            I.showInfor();
        } else {
            System.out.println("Not found!!!");
        }

    }

    private ArrayList<Injection> searchInjectedStubyName(String Sname) {
        ArrayList<Injection> nameList = new ArrayList<>();
        for (Injection I : this) {
            if (I.getsName().equalsIgnoreCase(Sname)) {
                nameList.add(I);
            }

        }

        return nameList;
    }

    public void searchInjectedStubyName() {
        String Sname = InputHandler.getString("Enter the student name that you want to search", "Please make sure your input is not empty!!!");
        ArrayList<Injection> nameList = searchInjectedStubyName(Sname);
        if (!nameList.isEmpty()) {
            printInjectionList(nameList);
        } else {
            System.out.println("Not found!!!");
        }

    }

    private void printInjectionList(ArrayList<Injection> Ilist) {
        System.out.printf("%5s|%25s|%30s|%15s|%30s|%30s|%30s|%30s|\n", "ID", "SID", "SName", "VID", "Date of first injection", "Place of 1st injection", "Date of second injection", "Place of 2nd injection");
        for (Injection injection : Ilist) {
            injection.showInfor();
        }
    }

}
