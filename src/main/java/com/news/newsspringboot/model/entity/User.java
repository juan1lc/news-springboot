package com.news.newsspringboot.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.news.newsspringboot.enums.Gender;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "com.news.newsspringboot.utils.KsuidIdentifierGenerator")
    //@Column(name = "id", nullable = false)
    private String id;


    @Column(unique = true)
    @NotEmpty(message = "用户名不能为空")
    @Size(min=2, max=64, message = "用户名长度应在2~64个字符之间")
    private String username;

    private String role_id;

    private String mail;

    private String phone;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String birth;

    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String photo="assets/images/defaultAvatar.png";

    private String introduction="此用户很懒..暂时没有介绍";

}
