package org.j2os.common;

import java.net.InetAddress;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.concurrent.TimeUnit;

public class CommonClass {
    private CommonClass() {}
    private static final CommonClass COMMON_CLASS = new CommonClass() ;
    public static CommonClass getInstance() {
        return COMMON_CLASS;
    }
    //***************************************************************
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    //*******For Save DATE  In Oracle DB
    public String getCurrentDateTimeAppServer () throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    public Date stringToSqlDate(String strDate) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date uDate = sdf1.parse(strDate);
        Date sqlDate = new Date(uDate.getTime());
        return  sqlDate;

    }
    public java.util.Date stringToUtilDate(String date) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-DD");
        java.util.Date uDate = (Date) sdf1.parse(date);
        return uDate;
    }
    //****************DecimalFormat
    /*customFormat("###,###.###", 123456.789);
      customFormat("###.##", 123456.789);
      customFormat("000000.000", 123.78);
      customFormat("$###,###.###", 12345.67);*/
    public String customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
    }
    //*******Get Client IP address
    public String getClientIpAddress () throws Exception {
        InetAddress IP = InetAddress.getLocalHost();
        return  IP.toString();
    }
    public int dayCountBetweenTwoDate (String startDate , String endDate) throws Exception {
        //Parsing the date
        SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-DD");
        java.util.Date startDateString = sdf1.parse(startDate);
        java.util.Date endDateString = sdf1.parse(endDate);
        //calculating number of days in between
        long noOfDaysBetween = ChronoUnit.DAYS.between((Temporal) startDateString, (Temporal) endDateString);
        return (int) noOfDaysBetween;
    }
    public String getDifferenceTime(String startDate , String endDate)  throws Exception{
        TimeUnit timeUnit = TimeUnit.SECONDS;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        java.util.Date d1 = sdf.parse( startDate.substring(0,19) );
        java.util.Date d2 = sdf.parse( endDate.substring(0,19) );
        long diffInMilli = d2.getTime() - d1.getTime();
        long s = timeUnit.convert(diffInMilli, TimeUnit.MILLISECONDS);

        long days = s / (24 * 60 * 60);
        long rest = s - (days * 24 * 60 * 60);
        long hrs = rest / (60 * 60);
        long rest1 = rest - (hrs * 60 * 60);
        long min = rest1 / 60;
        long sec = s % 60;
        String diff = "";
        //  if (days > 0) diff = days + " Days ";
        if (days > 0) hrs += (days * 24) ;

        String hrsStr = String.valueOf(hrs);
        //   if( hrsStr.length() <= 1 ) hrsStr += "0" +hrsStr;
        diff += hrsStr + ":";
        String minStr = String.valueOf(min);
        // if( minStr.length() <= 1 ) minStr += "0" +minStr;

        diff += minStr + ":";
        String secStr = String.valueOf(sec);
        //  if( secStr.length() <= 1 ) secStr += "0" + secStr;
        diff += secStr ;
        return diff;
    }

    // USE FULLLLLLL
    public String getDifferenceTwoTimestamp(Timestamp startDate , Timestamp endDate)  throws Exception{
        TimeUnit timeUnit = TimeUnit.SECONDS;
        long diffInMilli = endDate.getTime() - startDate.getTime();
        long s = timeUnit.convert(diffInMilli, TimeUnit.MILLISECONDS);
        long days = s / (24 * 60 * 60);
        long rest = s - (days * 24 * 60 * 60);
        long hrs = rest / (60 * 60);
        long rest1 = rest - (hrs * 60 * 60);
        long min = rest1 / 60;
        long sec = s % 60;
        String diff = "";
        //  if (days > 0) diff = days + " Days ";
        if (days > 0) hrs += (days * 24) ;

        String hrsStr = String.valueOf(hrs);
        //   if( hrsStr.length() <= 1 ) hrsStr += "0" +hrsStr;
        diff += hrsStr + ":";
        String minStr = String.valueOf(min);
        // if( minStr.length() <= 1 ) minStr += "0" +minStr;

        diff += minStr + ":";
        String secStr = String.valueOf(sec);
        //  if( secStr.length() <= 1 ) secStr += "0" + secStr;
        diff += secStr ;
        return diff;
    }

    //****************************************************************
    // USE FULLLLLLL MAy BE
    public Date convertFromJavaDateToSqlDate(java.util.Date javaDate) {
        Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new Date(javaDate.getTime());
        }
        return sqlDate;
    }
    //***********************************************
    public int getAge(Date birthdate )  throws Exception{
        TimeUnit timeUnit = TimeUnit.SECONDS;
        long nowDate = new java.util.Date().getTime();
        long diffInMilli = birthdate.getTime() - nowDate;
        long s = timeUnit.convert(diffInMilli, TimeUnit.MILLISECONDS);
        long age = s / (365 * 24 * 60 * 60);
        return (int) age;
    }
}
