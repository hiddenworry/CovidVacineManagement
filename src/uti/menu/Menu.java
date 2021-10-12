/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uti.menu;

import util.InputHandler;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class Menu {
     String title;
    List <String> Options = new ArrayList<> (  );
    //  Tạo mới một đối tượng "Menu", sử dụng lớp ArrayList để tạo 1 danh sách gồm các option thuộc kiểu String
    // Việc này giúp các thao tác trên Menu như thêm, xóa, sửa các option hoặc in menu trở nên thuận tiện hơn

    public Menu() {
    }
    

    public Menu(String title) {
        this.title = title;
    }
    public void addOptions(String Option) {
        Options.add (Option);
    }
    // Sử dụng hàm add có sẵn trong phân lớp "ArrayList" để thêm 1 Option vào Menu
    public void printMenu() {
         System.out.println("=====================================================================================");
        System.out.println ( title );
        Options.forEach (p -> System.out.println (p));
        // vòng lặp for each kiểu rút gọn để in từng option đã thêm vào Menu thông qua hàm "addOption" trước đó
    }
     public static boolean ContinueMessage(String Msg) {   String input;
        do {
             input = InputHandler.getString (Msg + " (Y/N)?","Please make sure your input is not empty!!!");
             if ( input.equalsIgnoreCase ("Y") || input.equalsIgnoreCase ("yes") )
                 return true;
             else if (input.equalsIgnoreCase ("N")|| input.equalsIgnoreCase ("no"))
                 return false;
             else System.out.println ( "Please make sure your input is correct" );
        } while ( true );
    /// hàm này trả về true false tương ứng với giá trị Yes hoặc No để báo cho chương trình biết có được tiếp tục nhập dữ liệu hay không
    }

    public int getChoice() {
        return  InputHandler.getAnInteger("You are going to enter value from 1 to " + this.Options.size() , "Invalid choice, please input again", 1, this.Options.size());
    }
    // Hàm getChoice giúp sử dung hàm getANumber trong lớp InputHandler thuộc package util
    // hàm này sẽ trả về giá trị kiểu số nguyên tương ứng trong khoản số option mà người dùng đã thêm
    // vào menu, nếu đầu vào đúng sẽ in ra "Input your choices here"  và trả về giá trị, 
    //ngược lại, nếu đầu vào sai thì sẽ in ra "please input again" và thông báo cho người dùng nhập lại
}
