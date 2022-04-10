package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.model.dto.SecondCommentCreateDto;
import com.news.newsspringboot.model.entity.article.ArticleComment;
import com.news.newsspringboot.model.entity.article.ArticleSecondComment;
import com.news.newsspringboot.model.entity.post.PostComment;
import com.news.newsspringboot.model.entity.post.PostSecondComment;
import com.news.newsspringboot.model.mapper.ArticleSecondCommentMapper;
import com.news.newsspringboot.model.mapper.PostSecondCommentMapper;
import com.news.newsspringboot.model.vo.SecondCommentVo;
import com.news.newsspringboot.repository.ArticleCommentRepository;
import com.news.newsspringboot.repository.ArticleSecondCommentRepository;
import com.news.newsspringboot.repository.PostCommentRepository;
import com.news.newsspringboot.repository.PostSecondCommentRepository;
import com.news.newsspringboot.service.SecondCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecondCommentServiceImpl implements SecondCommentService {

    ArticleSecondCommentRepository articleSecondCommentRepository;
    ArticleCommentRepository articleCommentRepository;
    ArticleSecondCommentMapper articleSecondCommentMapper;
    PostSecondCommentRepository postSecondCommentRepository;
    PostCommentRepository postCommentRepository;
    PostSecondCommentMapper postSecondCommentMapper;

    @Override
    public ArticleSecondComment createArticleSecondComment(SecondCommentCreateDto secondCommentCreateDto) {
        ArticleSecondComment articleSecondComment = new ArticleSecondComment();

        articleSecondComment.setCommentid(secondCommentCreateDto.getCommentid());
        articleSecondComment.setCommentparent(secondCommentCreateDto.getCommentparent());
        articleSecondComment.setContent(secondCommentCreateDto.getContent());
        articleSecondComment.setUserid(secondCommentCreateDto.getUserid());

        ArticleComment articleComment = articleCommentRepository.getById(secondCommentCreateDto.getCommentid());
        articleComment.setCommentcount(articleComment.getCommentcount()+1);
        articleCommentRepository.save(articleComment);
        return articleSecondCommentRepository.save(articleSecondComment);
    }

    @Override
    public List<SecondCommentVo> getArticleSecondCommentList(String commentid) {
        List<ArticleSecondComment> articleSecondComments = articleSecondCommentRepository.findByCommentidOrderByCommentlikeDesc(commentid);
        List<SecondCommentVo> secondCommentVos=new ArrayList<>();
        for(ArticleSecondComment articleSecondComment:articleSecondComments){
            secondCommentVos.add(articleSecondCommentMapper.toVo(articleSecondComment));
        }
        return secondCommentVos;
    }

    @Override
    public PostSecondComment createPostSecondComment(SecondCommentCreateDto secondCommentCreateDto) {
        PostSecondComment postSecondComment = new PostSecondComment();

        postSecondComment.setCommentid(secondCommentCreateDto.getCommentid());
        postSecondComment.setCommentparent(secondCommentCreateDto.getCommentparent());
        postSecondComment.setContent(secondCommentCreateDto.getContent());
        postSecondComment.setUserid(secondCommentCreateDto.getUserid());

        PostComment postComment = postCommentRepository.getById(secondCommentCreateDto.getCommentid());
        postComment.setCommentcount(postComment.getCommentcount()+1);
        postCommentRepository.save(postComment);
        return postSecondCommentRepository.save(postSecondComment);
    }

    @Override
    public List<SecondCommentVo> getPostSecondCommentList(String commentid) {
        List<PostSecondComment> postSecondComments = postSecondCommentRepository.findByCommentidOrderByCommentlikeDesc(commentid);
        List<SecondCommentVo> secondCommentVos=new ArrayList<>();
        for(PostSecondComment postSecondComment:postSecondComments){
            secondCommentVos.add(postSecondCommentMapper.toVo(postSecondComment));
        }
        return secondCommentVos;
    }

    @Autowired
    public void setArticleSecondCommentRepository(ArticleSecondCommentRepository articleSecondCommentRepository) {
        this.articleSecondCommentRepository = articleSecondCommentRepository;
    }
    @Autowired
    public void setArticleCommentRepository(ArticleCommentRepository articleCommentRepository) {
        this.articleCommentRepository = articleCommentRepository;
    }
    @Autowired
    public void setArticleSecondCommentMapper(ArticleSecondCommentMapper articleSecondCommentMapper) {
        this.articleSecondCommentMapper = articleSecondCommentMapper;
    }
    @Autowired
    public void setPostSecondCommentRepository(PostSecondCommentRepository postSecondCommentRepository) {
        this.postSecondCommentRepository = postSecondCommentRepository;
    }
    @Autowired
    public void setPostCommentRepository(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }
    @Autowired
    public void setPostSecondCommentMapper(PostSecondCommentMapper postSecondCommentMapper) {
        this.postSecondCommentMapper = postSecondCommentMapper;
    }
}
