package utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class converter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return dateFormat.parse(s);
        } catch (Exception e) {
            throw new RuntimeException("日期转换错误");
        }

    }

}
