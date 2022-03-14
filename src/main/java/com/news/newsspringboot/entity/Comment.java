package com.news.newsspringboot.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(generator = "ksuid")
    @GenericGenerator(name = "ksuid", strategy = "com.news.newsspringboot.utils.KsuidIdentifierGenerator")
    private String id;

    private Date publish_time;

    private String content;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="publisher_id",referencedColumnName = "id")
    private User user;
}
