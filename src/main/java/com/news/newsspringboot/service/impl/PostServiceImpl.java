package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.exception.BizException;
import com.news.newsspringboot.exception.ExceptionType;
import com.news.newsspringboot.model.dto.PostCreateRequestDto;
import com.news.newsspringboot.model.dto.PostUpdateRequestDto;
import com.news.newsspringboot.model.entity.Post;
import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.entity.comment.PostComment;
import com.news.newsspringboot.model.mapper.PostMapper;
import com.news.newsspringboot.model.vo.PostDetailsVo;
import com.news.newsspringboot.repository.PostCommentRepository;
import com.news.newsspringboot.repository.PostRepository;
import com.news.newsspringboot.service.PostService;
import com.news.newsspringboot.service.UserService;
import com.news.newsspringboot.utils.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    PostRepository repository;

    PostMapper postMapper;

    PostCommentRepository postCommentRepository;

    @Override
    public Post createPost(PostCreateRequestDto postCreateRequestDto, MultipartFile[] files) {
        log.info("#### 发布动态服务层，入参：postCreateRequestDto={}, files={}", postCreateRequestDto,files);
        try {
            // 处理图片上传
            String images = FileUploadUtil.uploadFile2Local(files);
            postCreateRequestDto.setPostImgs(images);
            log.info("#### 发布动态服务层，处理过程数据：images={},postCreateRequestDto={}",images,postCreateRequestDto);
            Post post = postMapper.createEntity(postCreateRequestDto);
            return repository.save(post);
        } catch (Exception e) {
            log.info("#### 发布动态服务层，发生异常，终止发布！");
            log.error("#### 发布动态服务层，异常错误终止，异常信息如下：",e);
            return null;
        }

    }


    @Override
    public Post publishDraft(Post post) {
        log.info("#### 动态服务层，发送草稿箱的动态：post={}",post);
        //检查动态是否是草稿
        checkPostStatics(post.getPoststatus());
        post.setUpdateTime(new Date());
        post.setPoststatus(0);
        return repository.save(post);
    }

    @Override
    public void delete(String postId) {
        log.info("#### 动态服务层，删除动态：postId={}",postId);
        Post post = getById(postId);
        repository.delete(post);
    }

    @Override
    public Page<Post> getDrafts(String userId, Pageable pageable) {
        return repository.findByUseridAndPoststatus(userId, 1, pageable);
    }

    @Override
    public Page<Post> getPosts(String userId, Pageable pageable) {
        return repository.findByUseridAndPoststatus(userId, 2, pageable);
    }

    @Override
    public Post updatePost(String postId, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = getById(postId);
        return postMapper.updateEntity(post, postUpdateRequestDto);
    }

    @Override
    public List<PostComment> getAllComments(String postId) {
        return postCommentRepository.findByPostid(postId);
    }

    @Override
    public PostDetailsVo getPostDetails(String postId) {
        Post post = repository.getById(postId);
        return postMapper.toDetailsVo(post);
    }

    private void checkPostStatics(Integer postStatus){
        log.info("#### 动态服务层，检查动态状态：postStatus={}",postStatus);
        if(postStatus != 1){
            throw new BizException(ExceptionType.POST_NOT_A_DRAFT);
        }
    }

    private Post getById(String id){
        return repository.getById(id);
    }

    @Autowired
    public void setRepository(PostRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Autowired
    public void setPostCommentRepository(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }
}
