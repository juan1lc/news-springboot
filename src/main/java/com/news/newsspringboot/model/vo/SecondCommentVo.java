package com.news.newsspringboot.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class SecondCommentVo {
    private String id;

    private Date createtime;

    private String content;

    private String userid;

    private String commentid;

    private String commentparent;

    private Integer commentlike;

    private String author;

    private String authorPhoto;

    private String parentName;
}
