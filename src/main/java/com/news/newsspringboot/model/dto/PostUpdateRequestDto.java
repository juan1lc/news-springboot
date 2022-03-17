package com.news.newsspringboot.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class PostUpdateRequestDto {

    private String postImgs;

    @NotBlank
    private String content;

    private String tags;

    //动态状态（0：发布，1：草稿）
    private Integer poststatus;

    //之后连接服务器获取ip，现在先默认广东广州
    private String location = "广东广州";

    private Date updateTime = new Date();
}
