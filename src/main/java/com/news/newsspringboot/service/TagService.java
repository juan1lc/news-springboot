package com.news.newsspringboot.service;

import com.news.newsspringboot.model.entity.Tag;

import java.util.List;

public interface TagService {
    Tag createTag(Tag tag);

    List<Tag> getAllTags();

    Tag getTag(String name);
}
