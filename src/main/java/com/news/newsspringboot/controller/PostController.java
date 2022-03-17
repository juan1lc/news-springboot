package com.news.newsspringboot.controller;

import com.news.newsspringboot.model.dto.PostCommentCreateDto;
import com.news.newsspringboot.model.dto.PostCreateRequestDto;
import com.news.newsspringboot.model.dto.PostUpdateRequestDto;
import com.news.newsspringboot.model.entity.comment.PostComment;
import com.news.newsspringboot.model.mapper.PostMapper;
import com.news.newsspringboot.model.vo.PostDetailsVo;
import com.news.newsspringboot.model.vo.PostVo;
import com.news.newsspringboot.model.vo.Response;
import com.news.newsspringboot.service.CommentService;
import com.news.newsspringboot.service.PostService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/posts")
@Api(tags="动态")
public class PostController {

    PostService postService;
    PostMapper postMapper;
    CommentService commentService;

    @PostMapping(value = "/publish-post", consumes = "multipart/form-data")
    PostVo create(@Validated PostCreateRequestDto postCreateRequestDto, MultipartFile[] files){
        PostVo postVo=postMapper.toVo(postService.createPost(postCreateRequestDto, files));
        return postVo;
    }

    @GetMapping("/{userId}/draftBox")
    Page<PostVo> draftBox(@PathVariable String userId, @PageableDefault(sort = {"update_time"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return postService.getDrafts(userId, pageable).map(postMapper::toVo);
    }

    @GetMapping("/{userId}/Allposts")
    Page<PostVo> Allposts(@PathVariable String userId, @PageableDefault(sort = {"update_time"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return postService.getPosts(userId, pageable).map(postMapper::toVo);
    }

    @GetMapping("/{id}/details")
    PostDetailsVo postDetail(@PathVariable("id") String postId){
        return postService.getPostDetails(postId);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        postService.delete(id);
        return;
    }

    @PutMapping("/{id}/edit")
    PostVo update(@PathVariable(value = "id") String id, PostUpdateRequestDto postUpdateRequestDto){
        return postMapper.toVo(postService.updatePost(id, postUpdateRequestDto));
    }


    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {this.commentService = commentService;}
}
