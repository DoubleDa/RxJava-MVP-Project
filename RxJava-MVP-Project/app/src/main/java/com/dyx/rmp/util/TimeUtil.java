package com.dyx.rmp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * project name：RxJava-MVP-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 下午11:27
 * alter person：dayongxin
 * alter time：16/8/3 下午11:27
 * alter remark：
 */
public class TimeUtil {
    /**
     * 时间戳转换成日期格式字符串
     *
     * @param timeStamp
     * @return
     */
    public static String timeStamp2Date(String timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(timeStamp)));
        return sd;
    }

}
