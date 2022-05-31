package com.shenzhou.logistics.utils;

import java.util.Random;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description:
 * @Date: 2021/11/27
 * @Time: 11:41
 **/
public class EmailUtil {
    public static String next() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }
}
