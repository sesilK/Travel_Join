package com.app.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStampUtil {

    public static String sysDate() {

        LocalDateTime dateTime = LocalDateTime.now();
        String formatedDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return formatedDateTime;


//        select TO_DATE('2023-06-23 14:06:42', 'YYYY-MM-DD HH24:MI:SS') time
//        from dual;
//
//        SELECT TO_CHAR( SYSDATE, 'YYYY-MM-DD HH24:MI:SS') AS converted_string
//        FROM dual;

    }
}
