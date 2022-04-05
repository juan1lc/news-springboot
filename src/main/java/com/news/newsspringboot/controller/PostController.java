package com.news.newsspringboot.controller;

import com.news.newsspringboot.model.dto.PostCommentCreateDto;
import com.news.newsspringboot.model.dto.PostCreateRequestDto;
import com.news.newsspringboot.model.dto.PostUpdateRequestDto;
import com.news.newsspringboot.model.entity.Post;
import com.news.newsspringboot.model.entity.PostLike;
import com.news.newsspringboot.model.entity.comment.PostComment;
import com.news.newsspringboot.model.mapper.PostMapper;
import com.news.newsspringboot.model.vo.PostDetailsVo;
import com.news.newsspringboot.model.vo.PostVo;
import com.news.newsspringboot.model.vo.Response;
import com.news.newsspringboot.service.CommentService;
import com.news.newsspringboot.service.PostService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.Utf8Decoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import static cn.hutool.core.lang.Console.print;

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

    @GetMapping(value = "/user-posts/{id}", produces = "application/json;charset=utf-8")
    List<Post> getUserPosts(@PathVariable(value = "id") String userid){
        return postService.getUserPosts(userid);
    }

    @GetMapping(value = "/user-drafts/{id}", produces = "application/json;charset=utf-8")
    List<Post> getUserDrafts(@PathVariable(value = "id") String userid){
        return postService.getUserDrafts(userid);
    }

    @PutMapping( "/publish-draft/{id}")
    Post PublishDraft(@PathVariable(value = "id") String postid){
        return postService.publishDraft(postid);
    }

    @GetMapping(value = "/tags", produces = "application/json;charset=utf-8")
    List<PostVo> getTagPosts(@RequestParam(value = "tag_name") String tag_name){
        try {
            String tags = URLDecoder.decode(tag_name, "UTF8");
            print(tags);
            print(tag_name);
            return postService.getPostByTags(tags);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/like")
    Integer likePost(@RequestParam(value = "postid") String postid,
                     @RequestParam(value = "userid") String userid){
        return postService.LikeDislikePost(postid, userid);
    }

    @GetMapping("/like/{postid}/{userid}")
    boolean likedPost(@PathVariable(value = "postid") String postid,
                      @PathVariable(value = "userid") String userid){
        return postService.CheckLikeTable(postid, userid);
    }

    @GetMapping(value = "/all-likers/{postid}", produces = "application/json;charset=utf-8")
    List<PostLike> getLikers(@PathVariable(value = "postid") String postid){
        return postService.getAllLikers(postid);
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
