/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.data.VaccinationList;
import java.io.IOException;
import uti.menu.Menu;

/**
 *
 * @author ADMIN
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // tạo một đối tượng Menu có tên là optionList và thêm vào 5 options và Titile
        int Choice;
        VaccinationList StVacList = new VaccinationList();
        String ContinueMsg = "Do you want to continues";
        Menu OptionList = new Menu("Welcome to covid 19 injection management systems - @ 2021 by <SE151333 - Nguyen Quoc Bao >");// đây là title
        OptionList.addOptions("1.Show information all students have been injected");
        OptionList.addOptions("2. Add student's vaccine injection information");
        OptionList.addOptions("3. Updating information of students' vaccine injection");
        OptionList.addOptions("4. Delete student vaccine injection information");
        OptionList.addOptions("5. Search for injection information by studentID");
        OptionList.addOptions("6. Quit");
        Menu submenu = new Menu("What do you want?");
        submenu.addOptions("1.Enter only the first injection");
        submenu.addOptions("2.Enter both injections");
        do {
            OptionList.printMenu();
            Choice = OptionList.getChoice();

//sử dụng hàm getChioce đã tạo sẵn trong lớp Menu.java để xử lý dữ liệu đảm bảo người dùng nhập vào
// 1 trong 5 lựa chọn
            switch (Choice) {
                case 1:

                    StVacList.showInjectedList();
                    break;
                case 2:
                    do {
                        StVacList.addStuInjection();
                    } while (Menu.ContinueMessage(ContinueMsg + " to add a new injection"));
                    break;
                case 3:
                        StVacList.updateInjection();
                    break;
                case 4:
                         StVacList.deleteInjection();
                    break;
                case 5:
                        StVacList.searchStuByID();
                    break;
                case 6:
                    System.out.println("Goodbye!!!");
                    StVacList.saveToFile();
                    break;
                default:
                    //System.out.println("Please enter a valid choice!!!!");
                   
                    break;

            }

        } while (Choice != 6);

    }
}
