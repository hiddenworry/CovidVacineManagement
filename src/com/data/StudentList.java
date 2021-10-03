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
 class StudentList extends ArrayList<Student> {

    final String dir = System.getProperty("user.dir") + "\\Database\\StudentList\\StList.dat";

    // đường dẫn lưu file StList.dat có sẵn
    public StudentList() {
        addData();
       
    }
    Student searchStudentByID(String ID) {
        for (Student St : this) {
            if(St.getsID().equalsIgnoreCase(ID)){
                return St;
            }
              
        }
        return null;

    }

// Add dữ liệu mẫu vào, giả sử ở đây là danh sách cách sinh viên có trong trường
    private void addData() {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(dir);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            StudentList.super.add(new Student("SE0000", "Nguyen Le"));
           StudentList.super.add(new Student("SE0001", "Nguyen Tran"));
            StudentList.super.add(new Student("SE0002", "Nguyen Hoang Le"));
            StudentList.super.add(new Student("SE0003", "Nguyen Hoang Anh"));
            StudentList.super.add(new Student("SE0004", "Nguyen Hoang Cao"));
           StudentList.super.add(new Student("SE0005", "Le Van Quang"));
            StudentList.super.add(new Student("SE0006", "Trinh Binh Minh"));
            StudentList.super.add(new Student("SE0007", "Nguyen Quoc Bao"));
            StudentList.super.add(new Student("SE0008", "Le Minh Thuan"));
            StudentList.super.add(new Student("SE0009", "Tran Dang Khoa"));
            StudentList.super.add(new Student("SE0010", "Le Chi Thanh"));
            StudentList.super.add(new Student("SE0011", "Nguyen Gia Bao"));
            for (Student StList : this) {
                
                oos.writeObject(StList);
            }
        } catch (Exception e) {
            

        }
        
    }
    
     String getStuID() {
        String Sid;

        do {
            Sid = InputHandler.getString("Enter Student ID[Example SE0001]:", "Sorry, make sure your input is not empty");
            if ( searchStudentByID(Sid) != null ) {
                return Sid;
            } else {
                System.out.println("This student is not in the school!!!");
            }

        } while (true);

    } // check mssv hợp lệ và có tồn tại trong danh sách

    
}
