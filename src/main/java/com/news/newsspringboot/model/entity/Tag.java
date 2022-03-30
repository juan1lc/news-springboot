package com.news.newsspringboot.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Tag {
    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "com.news.newsspringboot.utils.KsuidIdentifierGenerator")
    //@Column(name = "id", nullable = false)
    private String id;

    private String name;

    private String tagdesc;

    @Column(name = "create_time")
    private Date createTime = new Date();

    private Integer discussion;

}
