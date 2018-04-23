package online.ui.util;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by xymark.wang on 2017/11/1.
 */
public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    /**
     *
     * @param date 如2017-11-01 00:00:00
     * @param time 如"10:00:00"
     * @return 2017-11-01 10:00:00 的long型
     */
    public static Date addTimeToDate(Date date, String time){
        String todo = DateFormatUtils.format(date, "yyyy-MM-dd") + " " + time;
        Date result;
        try {
            result = DateUtils.parseDate(todo, new String[]{"yyyy-MM-dd HH:mm:ss"});
        } catch (ParseException e) {
            logger.error("日期转换出错：{}" , todo);
            return date;
        }
        return result;
    }
    public static Date parseDate(String date){
        Date result = null;
        try {
            result = DateUtils.parseDate(date, new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"});
        } catch (ParseException e) {
            logger.error("日期转换错误 {}", date);
        }
        return result;
    }

    public static String longToTimeString(long time){
        return DateFormatUtils.format(time - 8 * 60 * 60 * 1000, "HH:mm");
    }

    public static void main(String[] args) {
        long startDate = DateUtil.parseDate("2018-1-1 10:00:00").getTime();
        long endDate = DateUtil.parseDate("2018-01-01 12:00:00").getTime();
        System.out.println(longToTimeString(endDate - startDate));
    }
}
