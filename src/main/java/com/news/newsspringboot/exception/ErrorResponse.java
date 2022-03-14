package com.news.newsspringboot.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private Integer code;

    private String message;

    private Object trace; //路径信息
}
