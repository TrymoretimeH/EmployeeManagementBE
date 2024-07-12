package com.example.EmployeeManagement.Utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static Timestamp stringToTimestamp(String date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date1 = dateFormat.parse(date);
            Timestamp timestamp = new Timestamp(date1.getTime());
            return timestamp;

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Date stringToDate(String date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = dateFormat.parse(date);
            Date date2 = new Date(date1.getTime());
            return date2;

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
