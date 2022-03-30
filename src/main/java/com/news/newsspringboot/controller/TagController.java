package com.news.newsspringboot.controller;

import com.news.newsspringboot.model.dto.TagCreateDto;
import com.news.newsspringboot.model.entity.Tag;
import com.news.newsspringboot.model.mapper.TagMapper;
import com.news.newsspringboot.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tags")
public class TagController {
    TagService tagService;
    TagMapper tagMapper;

    @GetMapping(value = "/list", produces = "application/json;charset=utf-8")
    List<String> getAll(){
        List<String> res = new ArrayList<String>();
        List<Tag> tlist = tagService.getAllTags();
        for (Tag t: tlist) {
            res.add(t.getName());
        }
        return res;
    }

    @PostMapping
    Tag create(@Validated TagCreateDto tagDto) { return tagService.createTag(tagMapper.toTag(tagDto));}

    @GetMapping("/info")
    Tag getTag(@RequestParam(value = "name") String name){
        return tagService.getTag(name);
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }
}
