package com.shenzhou.logistics.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName ParamsUtils
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/15 7:35
 * @Version 1.0
 */
public class ParamsUtil {

    public static Map<String, String> ParamstoMap(HttpServletRequest httpServletRequest){
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = httpServletRequest.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码修复
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }
}
