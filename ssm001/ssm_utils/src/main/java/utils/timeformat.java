package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class timeformat {

    //日期转字符串
    public static String dateToString(Date date, String timestring) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timestring);
        return simpleDateFormat.format(date);
    }

    //字符串转日期
    public static Date stringToDate(String time1, String time2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(time2);
        return simpleDateFormat.parse(time1);
    }

}
