package com.news.newsspringboot.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SecondCommentCreateDto {

    @NotBlank
    private String content;

    private String userid;

    private String commentid;

    private String commentparent;
}
