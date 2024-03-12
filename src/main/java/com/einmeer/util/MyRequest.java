package com.einmeer.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 芊嵛
 * @date 2024/3/11
 */

/**
 * 为了能添加请求参数，默认只能修改
 */
public class MyRequest extends HttpServletRequestWrapper {
    Map<String, String[]> parameterMap = new HashMap<>();

    /**
     * 把原有的取出来
     * @param request
     */
    public MyRequest(HttpServletRequest request) {
        super(request);
        Map<String, String[]> parameterMap= request.getParameterMap();
        for (Map.Entry<String,String[]>entry: parameterMap.entrySet()){
            this.parameterMap.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 添加一个参数
     * @param key
     * @param value
     */
    public void addAttribute(String key,String value){
        if (parameterMap.containsKey(key)){
            String[] values = parameterMap.get(key);
            int length = values.length;
            values=Arrays.copyOf(values,length+1);
            values[length] = value;
        }else {
            parameterMap.put(key,new String[]{value});
        }
    }

    /**
     * 获取一个
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        if (parameterMap.containsKey(name)){
            return parameterMap.get(name)[0];
        }else {
            return null;
        }
    }

    /**
     * 获取全部
     * @return
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    /**
     *
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        if (parameterMap.containsKey(name)){
            return parameterMap.get(name);
        }else {
            return null;
        }
    }
}
