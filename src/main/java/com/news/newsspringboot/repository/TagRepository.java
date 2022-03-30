package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, String> {

    Tag getTagByName(String tagname);

    Optional<Tag> findByName(String tagname);

    @Query(value="select * from Tag t", nativeQuery=true)
    List<Tag> findAll();

}
