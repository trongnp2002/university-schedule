/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import static constant.Major.AI;
import static constant.Major.BA;
import static constant.Major.ES;
import static constant.Major.GD;
import static constant.Major.JS;
import static constant.Major.KS;
import static constant.Major.SE;

/**
 *
 * @author nguyn
 */
public class Validation {

    public static String getStudentRollNum(String major, int id, boolean isEmail) {
        String majorId = getStudentMajorId(major);
        if (isEmail) {
            majorId = majorId.toLowerCase();
        }
        String rollNum = majorId + id;
        return rollNum;
    }

    public static String getMailAuto(String name, int id, String major) {
        String nameEmail = convertNameEmail(name);
        String rollNum = getStudentRollNum(major, id, true);
        String mail = nameEmail + rollNum + "@fpt.edu.vn";
        return mail;
    }

    public static String convertNameEmail(String name) {
        String str[] = name.split(" ");
//        String nameEmail = str[2] + str[0].toUpperCase().charAt(0) + str[1].toUpperCase().charAt(0);
        String nameEmail = "";
        nameEmail = str[str.length - 1].toLowerCase();
        for (int i = 0; i < str.length - 1; i++) {
            nameEmail = nameEmail + str[i].toLowerCase().charAt(0);
        }
        return nameEmail;
    }

    public static void main(String[] args) {
        String name = convertNameEmail("Nguyen Phuc Trong Dep Trai");
        String mail = getMailAuto("Nguyen Phuc Trong", 163170, SE);
        String rollNum = getStudentRollNum(SE, 163170, true);
        String pass = getPasswordAuto("Nguyen Phuc Trong", SE, 163170);
        System.out.println(pass);
    }

    public static String getPasswordAuto(String name, String major, int id) {
        String nameEmail = convertNameEmail(name);
        String rollNum = getStudentRollNum(major, id, true);
        String pass = nameEmail + rollNum;
        return pass;
    }

    public static String getStudentMajorId(String major) {
        String majorId = "a";

        if (major.equalsIgnoreCase(SE) || major.equalsIgnoreCase(GD) || major.equalsIgnoreCase(AI)) {
            majorId = "HE";
        }
        if (major.equalsIgnoreCase(JS) || major.equalsIgnoreCase(KS) || major.equalsIgnoreCase(ES)) {
            majorId = "HA";
        }
        if (major.equalsIgnoreCase(BA)) {
            majorId = "HS";
        }
        return majorId;
    }

    public static String standardizedName(String name) {
        String name2 = name.replaceAll("\\s+", " ").trim();
        StringBuilder s = new StringBuilder(name2.toLowerCase());
        s.setCharAt(0, Character.toUpperCase(s.charAt(0)));
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                s.setCharAt(i + 1, Character.toUpperCase(s.charAt(i + 1)));
            }
        }
        return s.toString();
    }

    public static boolean checkPhoneNum(String phone) {
        try {
            int p = Integer.parseInt(phone);
            String str = phone.substring(0, 1);
            if (!str.equals("0")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static int getYearStudyInt(String year) {
        String str = year.substring(1, 3);
        int intYear = Integer.parseInt(str);
        return intYear;
    }

}
