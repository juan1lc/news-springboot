package com.news.newsspringboot.model.mapper.impl;

import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.entity.article.ArticleSecondComment;
import com.news.newsspringboot.model.mapper.ArticleSecondCommentMapper;
import com.news.newsspringboot.model.vo.SecondCommentVo;
import com.news.newsspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleSecondCommentMapperImpl implements ArticleSecondCommentMapper {

    UserRepository userRepository;

    @Override
    public SecondCommentVo toVo(ArticleSecondComment articleSecondComment) {
        if(articleSecondComment == null){
            return null;
        }
        String userId = articleSecondComment.getUserid();
        User user = userRepository.getById(userId);
        String userId2 = articleSecondComment.getCommentparent();
        User user2;

        SecondCommentVo secondCommentVo = new SecondCommentVo();

        secondCommentVo.setAuthor(user.getUsername());
        secondCommentVo.setAuthorPhoto(user.getPhoto());
        secondCommentVo.setUserid(userId);
        if(userId2!=null){
            user2 = userRepository.getById(userId2);
            secondCommentVo.setCommentparent(userId2);
            secondCommentVo.setParentName(user2.getUsername());
        }
        secondCommentVo.setCommentid(articleSecondComment.getCommentid());
        secondCommentVo.setContent(articleSecondComment.getContent());
        secondCommentVo.setCommentlike(articleSecondComment.getCommentlike());
        secondCommentVo.setCreatetime(articleSecondComment.getCreatetime());
        secondCommentVo.setId(articleSecondComment.getId());

        return secondCommentVo;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
