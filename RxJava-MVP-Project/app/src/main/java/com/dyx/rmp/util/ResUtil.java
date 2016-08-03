package com.dyx.rmp.util;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

/**
 * project name：RxJava-MVP-Project
 * class describe：获取资源文件的工具类
 * create person：dayongxin
 * create time：16/8/3 下午4:05
 * alter person：dayongxin
 * alter time：16/8/3 下午4:05
 * alter remark：
 */
public class ResUtil {

    /**
     * 从资源文件获取数组
     *
     * @param context
     * @param id
     * @return
     */
    public static List<String> getResArray(Context context, int id) {
        return Arrays.asList(context.getResources().getStringArray(id));
    }
}
