package com.einmeer.vo;


import lombok.Getter;

/**
 * @author 芊嵛
 * @date 2024/3/5
 */
enum ResultCode {
    SUCCESS(200),
    FAILED(500),
    UNLOGIN(401),
    FORBID(403);
    private Integer id;

    ResultCode(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

@Getter
public class ResultJson {
    private Integer code;
    private Object content;
    private String message;

    private ResultJson(Integer code, Object content, String message) {
        this.code = code;
        this.content = content;
        this.message = message;
    }

    public static ResultJson getInstance(ResultCode resultCode, Object content, String message) {
        return new ResultJson(resultCode.getId(), content, message);
    }

    // 二次封装SUCCESS
    // 三个都传
    public static ResultJson success(Object content, String message) {
        return getInstance(ResultCode.SUCCESS, content, message);
    }

    //  传成功和内容
    public static ResultJson success(Object content) {
        return success(content, null);
    }

    // 传成功和信息
    public static ResultJson success(String message) {
        return success(null, message);
    }


    // 二次封装FAILED
    // 三个都传
    public static ResultJson failed(Object content, String message) {
        return getInstance(ResultCode.FAILED, content, message);
    }

    //  传失败和内容
    public static ResultJson failed(Object content) {
        return failed(content, null);
    }

    // 传失败和信息
    public static ResultJson failed(String message) {
        return failed(null, message);
    }

    // 登录失败
    public static ResultJson unlogin(String message){
        return getInstance(ResultCode.UNLOGIN,null,message);
    }
}

