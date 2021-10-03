/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;


/**
 *
 * @author ADMIN
 */
class DateHandler {

    static boolean checkDate1stDose(String firDose, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withResolverStyle(ResolverStyle.STRICT);
       try {
            LocalDate lowerDate = LocalDate.parse("31/12/2019", formatter);// nhập quá thời gian sẽ chuyển qua tháng khác
            // vì vacine đầu tiên được sản xuất vào khoản năm 2020
             LocalDate date = LocalDate.parse(firDose, formatter);
            return date.isAfter(lowerDate);
       } catch(DateTimeParseException e) {
           return false;
       
       }
           
            
            
        
       
    }

    static boolean checkDate2ndDose(String firDose, String secDose, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                LocalDate firstDose = LocalDate.parse(firDose, formatter.withResolverStyle(ResolverStyle.STRICT));
                LocalDate secondDose = LocalDate.parse(secDose, formatter.withResolverStyle(ResolverStyle.STRICT));
                if (secondDose.isAfter(firstDose.plus(4, ChronoUnit.WEEKS)) && secondDose.isBefore(firstDose.plus(12, ChronoUnit.WEEKS))) {
                    return true;
                } // ngày tiêm muỗi 2 phải sau muỗi 1 từ 4 tơi 12 tuần
                else {
                    return false;
                }

            } catch (Exception e) {
                return false;

            }

        

    }
    public static void main(String[] args) {
        System.out.println(DateHandler.checkDate1stDose("28/2/2021", "d/M/uuuu"));
    }
}
