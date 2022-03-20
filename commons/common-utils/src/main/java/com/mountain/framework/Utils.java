package com.mountain.framework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    /**
     * 时间格式化
     */
    public static String fmtTime() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return fmt.format(time);
    }
}
