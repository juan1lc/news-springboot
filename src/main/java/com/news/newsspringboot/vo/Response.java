package com.news.newsspringboot.vo;

import lombok.Data;

@Data
public class Response {
    private boolean success;
    private String message;
    private Object body;

    //响应处理是否成功
    public boolean isSuccess(){
        return success;
    }
    public void setSuccess(boolean success){
        this.success = success;
    }

    public Response(boolean success, String message, Object body){
        this.success = success;
        this.message = message;
        this.body = body;
    }
}
