package com.news.newsspringboot.model.vo;

import com.news.newsspringboot.enums.Gender;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserVo {
    private String id;

    private String username;

    private String password;

    private String role_id;

    private String mail;

    private String phone;

    private String birth;

    private String address;

    private String gender;

    private String photo="/images/photos/defaulthead.jpg";

    private String introduction="此用户很懒..暂时没有介绍";
}
