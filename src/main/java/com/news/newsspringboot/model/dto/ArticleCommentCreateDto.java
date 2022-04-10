package com.news.newsspringboot.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ArticleCommentCreateDto {
    private Date createtime = new Date();

    @NotBlank
    private String content;

    private String userid;

    private String articleid;

    private String commentparent;

    private Integer commentlike=0;

    private String commentlikeuser;

    private Integer commentcount=0;
}
