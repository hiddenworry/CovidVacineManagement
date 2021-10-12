/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author ADMIN
 */
public class MD5Encrytion {
    public static String encrypt(Serializable srcText) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String encrText;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] srcTextBytes = srcText.toString().getBytes("UTF8");
        byte[] encryBytes = md.digest(srcTextBytes);
        BigInteger bigInteger = new BigInteger(1,encryBytes);
        encrText = bigInteger.toString(16);  
        return encrText;
    } 
     public static boolean verify(String srcText, String hashText)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String encrText;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] srcTextBytes = srcText.getBytes("UTF8");
        byte[] encryBytes = md.digest(srcTextBytes);
        BigInteger bigInteger = new BigInteger(1,encryBytes);
        encrText = bigInteger.toString(16); 
        return hashText.equals(encrText);
    }
  
    
  
}
