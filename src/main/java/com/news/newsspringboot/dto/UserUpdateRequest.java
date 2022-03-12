package com.news.newsspringboot.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserUpdateRequest {

    @Size(min = 2, max = 64, message = "用户名长度应该在2个字符到64个字符之间")
    private String username;
    private String gender;
    private String mail;
    private String phone;
    private String photo="src/main/resources/static/images/defaulthead.jpg";
    private String introduction="此用户很懒..暂时没有介绍";
}
