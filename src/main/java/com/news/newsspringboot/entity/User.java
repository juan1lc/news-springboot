package com.news.newsspringboot.entity;

import com.news.newsspringboot.utils.enums.Gender;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String mail;

    private String phone;

    private String photo="src/main/resources/static/images/defaulthead.jpg";

    private String introduction="此用户很懒..暂时没有介绍";


    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", password='" + password + '\'' +
                ", userName='" + username + '\'' +
                ", gender='" + gender.toString() + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }

}
