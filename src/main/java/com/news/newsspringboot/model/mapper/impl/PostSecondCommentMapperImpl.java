package com.news.newsspringboot.model.mapper.impl;

import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.entity.post.PostSecondComment;
import com.news.newsspringboot.model.mapper.PostSecondCommentMapper;
import com.news.newsspringboot.model.vo.SecondCommentVo;
import com.news.newsspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostSecondCommentMapperImpl implements PostSecondCommentMapper {

    UserRepository userRepository;

    @Override
    public SecondCommentVo toVo(PostSecondComment postSecondComment) {
        if(postSecondComment == null){
            return null;
        }
        String userId = postSecondComment.getUserid();
        User user = userRepository.getById(userId);
        String userId2 = postSecondComment.getCommentparent();
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
        secondCommentVo.setCommentid(postSecondComment.getCommentid());
        secondCommentVo.setContent(postSecondComment.getContent());
        secondCommentVo.setCommentlike(postSecondComment.getCommentlike());
        secondCommentVo.setCreatetime(postSecondComment.getCreatetime());
        secondCommentVo.setId(postSecondComment.getId());

        return secondCommentVo;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
