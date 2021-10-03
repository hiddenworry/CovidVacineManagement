/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class InputHandler {

    private static final Scanner sc = new Scanner(System.in);
    // Hàm nhận input và trả về 1 chuổi khác rỗng

    public static String getString(String Msg, String errorMsg) {
        String InputString;
        do {
            System.out.println(Msg);
            InputString = sc.nextLine().trim();
            if (InputString.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return InputString;
            }
        } while (true);

    }
    // Hàm nhận inputvaf trả về 1 chuỗi khác rỗng có độ dài bé hơn hoặc bằng biến length được truyền vào

    public static String getString(String Msg, String errorMsg, int length) {
        String InputString;
        do {
            System.out.println(Msg);
            InputString = sc.nextLine().trim();
            if (InputString.isEmpty()) {
                System.out.println(errorMsg);
            } else if (InputString.length() > length) {
                System.out.println("Your input must under " + length + " words");
            } else {
                return InputString;
            }
        } while (true);

    }

    // Hàm nhận nput và trả về 1 số nguyên
    public static int getAnInteger(String Msg, String errorsMsg) {
        int Input;
        do {
            try {
                System.out.println(Msg);
                Input = Integer.parseInt(sc.nextLine());
                return Input;
            } catch (Exception e) {
                System.out.println(errorsMsg);
            }
        } while (true);

    }

    public static int getAnInteger(String Msg, String errorsMsg, int lowerBound, int upperBound) {
        int Input;
        do {
            try {
                System.out.println(Msg);
                Input = Integer.parseInt(sc.nextLine());
                if (Input < lowerBound || Input > upperBound) {
                    throw new Exception();
                }
                return Input;
            } catch (Exception e) {
                System.out.println(errorsMsg);
            }
        } while (true);

    }
    
     public static String checkDate1stDose(String Msg, String errorMsg) {
        String date = "";
        boolean isDate = false;
        do {
            try {
                System.out.println(Msg);
                date = sc.nextLine().trim();// nhận vào 1 chuỗi và loại bỏ hết dấu cách ở đầu và cuối, sử dụng nextLine để loại bỏ hết kí tự rác trong ram
                isDate = DateHandler.checkDate1stDose(date, "d/M/uuuu");
                // gọi hàm isvalidDate ở bên dưới để check thông tin người dùng nhập vào
                if (!isDate) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        } while (!isDate);

        return date;
    }
     public static String checkDate2ndDose(String firtDose,String Msg, String errorMsg){
         String secondDose = "";
        boolean isDate = false;
        do {
            try {
                System.out.println(Msg);
                secondDose = sc.nextLine().trim();// nhận vào 1 chuỗi và loại bỏ hết dấu cách ở đầu và cuối, sử dụng nextLine để loại bỏ hết kí tự rác trong ram
                isDate = DateHandler.checkDate2ndDose(firtDose, secondDose, "d/M/uuuu");
                // gọi hàm isvalidDate ở bên dưới để check thông tin người dùng nhập vào
                if (!isDate) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        } while (!isDate);

        return secondDose;
     
     
     }
}
