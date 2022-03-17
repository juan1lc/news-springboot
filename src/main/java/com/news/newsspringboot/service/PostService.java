package com.news.newsspringboot.service;

import com.news.newsspringboot.model.dto.PostCreateRequestDto;
import com.news.newsspringboot.model.dto.PostUpdateRequestDto;
import com.news.newsspringboot.model.entity.Post;
import com.news.newsspringboot.model.entity.comment.PostComment;
import com.news.newsspringboot.model.vo.PostDetailsVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService{

    //发布动态/存入草稿
    Post createPost(PostCreateRequestDto postCreateRequestDto, MultipartFile[] files);

    //发布草稿箱中的动态
    Post publishDraft(Post post);

    //删除动态
    void delete(String postId);

    //获取某用户草稿箱中的全部草稿
    Page<Post> getDrafts(String userId, Pageable pageable);

    //获取某用户全部动态
    Page<Post> getPosts(String userId, Pageable pageable);

    //修改草稿
    Post updatePost(String postId, PostUpdateRequestDto postUpdateRequestDto);

    //获取某动态所有评论
    List<PostComment> getAllComments(String postId);

    //获取动态详情页
    PostDetailsVo getPostDetails(String postId);
}
