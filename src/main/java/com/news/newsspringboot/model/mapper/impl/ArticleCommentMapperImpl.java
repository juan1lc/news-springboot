package com.news.newsspringboot.model.mapper.impl;

import com.news.newsspringboot.model.dto.ArticleCommentCreateDto;
import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.entity.article.ArticleComment;
import com.news.newsspringboot.model.mapper.ArticleCommentMapper;
import com.news.newsspringboot.model.vo.ArticleCommentVo;
import com.news.newsspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleCommentMapperImpl implements ArticleCommentMapper {
    UserRepository userRepository;

    @Override
    public ArticleComment articleCommentCreateEntity(ArticleCommentCreateDto articleCommentCreateDto) {
        if(articleCommentCreateDto == null){
            return null;
        }

        ArticleComment articleComment = new ArticleComment();

        articleComment.setArticleid(articleCommentCreateDto.getArticleid());
        articleComment.setCommentcount(articleCommentCreateDto.getCommentcount());
        articleComment.setCommentlike(articleCommentCreateDto.getCommentlike());
        articleComment.setCommentlikeuser(articleCommentCreateDto.getCommentlikeuser());
        articleComment.setCommentparent(articleCommentCreateDto.getCommentparent());
        articleComment.setContent(articleCommentCreateDto.getContent());
        articleComment.setUserid(articleCommentCreateDto.getUserid());
        articleComment.setCreatetime(articleCommentCreateDto.getCreatetime());

        return articleComment;
    }

    @Override
    public ArticleCommentVo toVo(ArticleComment articleComment) {
        if(articleComment == null){
            return null;
        }

        String userId = articleComment.getUserid();
        User user = userRepository.getById(userId);

        ArticleCommentVo articleCommentVo = new ArticleCommentVo();

        articleCommentVo.setAuthor(user.getUsername());
        articleCommentVo.setAuthorPhoto(user.getPhoto());
        articleCommentVo.setCommentlike(articleComment.getCommentlike());
        articleCommentVo.setCommentcount(articleComment.getCommentcount());
        articleCommentVo.setCommentparent(articleComment.getCommentparent());
        articleCommentVo.setCreatetime(articleComment.getCreatetime());
        articleCommentVo.setUserid(articleComment.getUserid());
        articleCommentVo.setId(articleComment.getId());
        articleCommentVo.setContent(articleComment.getContent());

        return articleCommentVo;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
