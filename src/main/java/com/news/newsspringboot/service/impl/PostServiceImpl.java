package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.exception.BizException;
import com.news.newsspringboot.exception.ExceptionType;
import com.news.newsspringboot.model.dto.PostCreateRequestDto;
import com.news.newsspringboot.model.dto.PostUpdateRequestDto;
import com.news.newsspringboot.model.entity.post.PostLike;
import com.news.newsspringboot.model.entity.post.Post;
import com.news.newsspringboot.model.entity.post.PostComment;
import com.news.newsspringboot.model.entity.post.PostStar;
import com.news.newsspringboot.model.mapper.PostCommentMapper;
import com.news.newsspringboot.model.mapper.PostMapper;
import com.news.newsspringboot.model.vo.PostCommentVo;
import com.news.newsspringboot.model.vo.PostDetailsVo;
import com.news.newsspringboot.model.vo.PostLikePreview;
import com.news.newsspringboot.model.vo.PostVo;
import com.news.newsspringboot.repository.PostCommentRepository;
import com.news.newsspringboot.repository.PostLikeRepository;
import com.news.newsspringboot.repository.PostRepository;
import com.news.newsspringboot.repository.PostStarRepository;
import com.news.newsspringboot.service.PostLikeService;
import com.news.newsspringboot.service.PostService;
import com.news.newsspringboot.utils.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    PostRepository repository;

    PostMapper postMapper;

    PostCommentMapper postCommentMapper;

    PostCommentRepository postCommentRepository;

    PostLikeRepository postLikeRepository;

    PostLikeService likePostService;

    PostStarRepository postStarRepository;


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
    public Post publishDraft(String postId) {
        Post post = getById(postId);
        post.setUpdateTime(new Date());
        post.setPoststatus(0);
        return repository.save(post);
    }

    @Override
    public Post getPostById(String postId) {
        return getById(postId);
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
        return repository.findByUseridAndPoststatus(userId, 0, pageable);
    }

    @Override
    public List<Post> getUserPosts(String userId) {
        return repository.findAllByUseridAndPoststatus(userId, 0);
    }

    @Override
    public List<Post> getUserDrafts(String userId) {
        return repository.findAllByUseridAndPoststatus(userId, 1);
    }

    @Override
    public List<PostVo> getPostByTags(String tags) {
        List<Post> list = new ArrayList<Post>();
        List<PostVo> listVo = new ArrayList<PostVo>();
        list = repository.findAllByTagsAndPoststatus(tags, 0);
        for (Post post : list) {
            listVo.add(postMapper.toVo(post));
        }
        return listVo;
    }

    @Override
    public List<PostVo> getHotPostByTags(String tags) {
        List<Post> list;
        List<Post> list2 = new ArrayList<>();
        List<PostVo> listVo = new ArrayList<PostVo>();
        list = repository.findAllByTagsAndPoststatus(tags, 0);

        if(list.size()>20){
            for(int i=0; i<20; i++){
                list2.add(list.get(i));
            }
        }else{
            list2 = list;
        }
        list2.sort((o1, o2) -> {
            if(o1.getId()==null || o2.getId()==null){
                return 0;
            }else if(o1.getPostLike()+o1.getCommentCount()==o2.getPostLike()+o2.getCommentCount()){
                return o2.getPostBrowse().compareTo(o1.getPostBrowse());
            }else{
                return o2.getPostLike()-o1.getPostLike();
            }
        });
        for (Post post : list2) {
            listVo.add(postMapper.toVo(post));
        }
        return listVo;
    }

    @Override
    public List<PostLike> getAllLikers(String postid) {
        return postLikeRepository.findAllByPostid(postid);
    }


    @Override
    public Post updatePost(String postId, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = getById(postId);
        return postMapper.updateEntity(post, postUpdateRequestDto);
    }

    @Override
    public Integer addBrowse(String postid) {
        Post post = repository.getById(postid);
        Integer browse = post.getPostBrowse();
        post.setPostBrowse(browse+1);
        repository.save(post);
        return browse+1;
    }

    @Override
    public List<PostCommentVo> getAllComments(String postId) {
        List<PostComment> postComments = postCommentRepository.findByPostidOrderByCommentlikeDesc(postId);
        List<PostCommentVo> postCommentVos= new ArrayList<>();
        for(PostComment postComment:postComments){
            postCommentVos.add(postCommentMapper.toVo(postComment));
        }
        return postCommentVos;
    }

    @Override
    public PostDetailsVo getPostDetails(String postId) {
        Post post = repository.getById(postId);
        return postMapper.toDetailsVo(post);
    }

    @Override
    public Integer LikeDislikePost(String postId, String userId) {
        Post post = getById(postId);
        Integer likeNum = post.getPostLike();
        boolean isLiked = CheckLikeTable(postId, userId);
        if(isLiked){//取消喜欢
            PostLike likePost = postLikeRepository.getLikePostByUseridAndPostid(userId, postId);
            postLikeRepository.delete(likePost);
            likeNum--;
        }else{
            likePostService.likePost(userId, postId);
            likeNum++;
        }
        post.setPostLike(likeNum);
        repository.save(post);
        return likeNum;
    }

    @Override
    public List<PostLikePreview> getUserLike(String userId) {
        List<PostLike> postLikes = postLikeRepository.findAllByUserid(userId);
        List<PostLikePreview> res = new ArrayList<>();
        for(PostLike pl:postLikes) {
            Post post = repository.getById(pl.getPostid());
            res.add(postMapper.toLikePreview(post, pl.getLiketime()));
        }
        return res;
    }

    @Override
    public List<PostLikePreview> getUserStar(String userId) {
        List<PostStar> postStars = postStarRepository.findAllByUseridOrderByLiketimeDesc(userId);
        List<PostLikePreview> res = new ArrayList<>();
        for(PostStar pl:postStars){
            Post post = repository.getById(pl.getPostid());
            res.add(postMapper.toLikePreview(post, pl.getLiketime()));
        }
        return res;
    }

    public boolean CheckLikeTable(String postId, String userId) {
        Optional<PostLike> likePost = postLikeRepository.findByPostidAndUserid(postId, userId);
        return likePost.isPresent();
    }

    @Override
    public Integer getPostCount(String userid) {
        List<Post> posts = repository.findAllByUseridAndPoststatus(userid, 0);
        return posts.size();
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
    public void setPostCommentMapper(PostCommentMapper postCommentMapper) {
        this.postCommentMapper = postCommentMapper;
    }

    @Autowired
    public void setPostCommentRepository(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }

    @Autowired
    public void setPostLikeRepository(PostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }

    @Autowired
    public void setLikePostService(PostLikeService likePostService) {
        this.likePostService = likePostService;
    }

    @Autowired
    public void setPostStarRepository(PostStarRepository postStarRepository) {
        this.postStarRepository = postStarRepository;
    }
}
