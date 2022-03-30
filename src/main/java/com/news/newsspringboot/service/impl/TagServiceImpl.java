package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.exception.BizException;
import com.news.newsspringboot.exception.ExceptionType;
import com.news.newsspringboot.model.entity.Tag;
import com.news.newsspringboot.repository.TagRepository;
import com.news.newsspringboot.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TagServiceImpl implements TagService {
    TagRepository tagRepository;

    @Override
    public Tag createTag(Tag tag) {
        checkTagName(tag.getName());
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTag(String name) {
        return tagRepository.getTagByName(name);
    }

    private void checkTagName(String name) {
        Optional<Tag> tag = tagRepository.findByName(name);
        if(tag.isPresent()){
            throw new BizException(ExceptionType.TAG_DUPLICATE);
        }
    }

    @Autowired
    public void setTagRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
}
