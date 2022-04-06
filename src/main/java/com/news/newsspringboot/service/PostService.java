package com.news.newsspringboot.service;

import com.news.newsspringboot.model.dto.PostCreateRequestDto;
import com.news.newsspringboot.model.dto.PostUpdateRequestDto;
import com.news.newsspringboot.model.entity.post.Post;
import com.news.newsspringboot.model.entity.post.PostLike;
import com.news.newsspringboot.model.entity.comment.PostComment;
import com.news.newsspringboot.model.vo.PostDetailsVo;
import com.news.newsspringboot.model.vo.PostLikePreview;
import com.news.newsspringboot.model.vo.PostVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService{

    //发布动态/存入草稿
    Post createPost(PostCreateRequestDto postCreateRequestDto, MultipartFile[] files);

    //发布草稿箱中的动态
    Post publishDraft(String postId);

    Post getPostById(String postId);

    //删除动态
    void delete(String postId);

    //获取某用户草稿箱中的全部草稿
    Page<Post> getDrafts(String userId, Pageable pageable);

    //获取某用户全部动态
    Page<Post> getPosts(String userId, Pageable pageable);

    List<Post> getUserPosts(String userId);

    List<Post> getUserDrafts(String userId);

    List<PostVo> getPostByTags(String tags);

    List<PostLike> getAllLikers(String postid);

    //修改草稿
    Post updatePost(String postId, PostUpdateRequestDto postUpdateRequestDto);

    //获取某动态所有评论
    List<PostComment> getAllComments(String postId);

    //获取动态详情页
    PostDetailsVo getPostDetails(String postId);

    Integer LikeDislikePost(String postId, String userId);

    List<PostLikePreview> getUserLike(String userId);

    List<PostLikePreview> getUserStar(String userId);

    boolean CheckLikeTable(String postId, String userId);
}
