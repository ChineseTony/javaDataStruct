package com.tom.zhanbao;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WangTao
 */
public class DateFormatUtil {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private DateFormatUtil(){

    }

    private static final ThreadLocal<SimpleDateFormat> dateFormatThreadLocal;


    static {
        dateFormatThreadLocal =  ThreadLocal.withInitial(() -> {
            return new SimpleDateFormat(DEFAULT_FORMAT);
        });
    }

    public static String format(Date date){
        return dateFormatThreadLocal.get().format(date);
    }

    public static void close(){
        dateFormatThreadLocal.remove();
    }
}
