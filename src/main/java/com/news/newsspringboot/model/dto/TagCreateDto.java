package com.news.newsspringboot.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TagCreateDto {
    private String name;

    private String tagdesc;

    private Date createTime = new Date();

    private Integer discussion = 0;
}
