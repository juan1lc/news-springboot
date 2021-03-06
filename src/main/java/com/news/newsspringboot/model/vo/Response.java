package com.news.newsspringboot.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通用响应基类
 * @version 1.0
 */
@Data
public class Response<T>  implements Serializable {
    /**
     * 版本号
     */
    private String ver = "1.0.0.0";
    /**
     * 时间戳
     */
    private String timestamp = LocalDateTime.now().toString();
    /**
     * 提示信息
     */
    private String message;
    /**
     * 状态码 （暂定为 0：成功，1：失败）
     */
    private Integer statusCode;
    /**
     * 响应结果
     */
    private T res;

    /**
     * 错误响应构造器(私有化)
     * @param message
     * @param statusCode
     * @param res
     */
    private Response(String message, Integer statusCode,T res){
        this.message = message;
        this.statusCode= statusCode;
        this.res = res;
    }

    public static <T> Response responseFail(String message,T res){
        return new Response<>(message, 1,res);
    }

    public static <T> Response responseSuccess(String message,T res){
        return new Response<>(message, 0,res);
    }
}
