package com.news.newsspringboot.model.mapper;

import com.news.newsspringboot.model.entity.article.Article;
import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.vo.ArticleLikePreview;
import com.news.newsspringboot.model.vo.ArticlePreview;
import com.news.newsspringboot.model.vo.ArticleVo;
import com.news.newsspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ArticleMapperImpl implements ArticleMapper{
    UserRepository userRepository;

    @Override
    public ArticleVo toVo(Article article) {
        if(article==null){
            return null;
        }

        String userId = article.getUserId();
        User user = userRepository.getById(userId);

        ArticleVo articleVo = new ArticleVo();
        articleVo.setAuthorId(userId);
        articleVo.setArticle(article);
        articleVo.setAuthor(user.getUsername());
        articleVo.setAuthorPhoto(user.getPhoto());

        return articleVo;

    }

    @Override
    public ArticlePreview toPreview(Article article) {
        if(article==null){
            return null;
        }

        String userId = article.getUserId();
        User user = userRepository.getById(userId);

        ArticlePreview articlePreview = new ArticlePreview();
        articlePreview.setId(article.getId());
        articlePreview.setAuthorId(user.getId());
        articlePreview.setAuthor(user.getUsername());
        articlePreview.setAuthorPhoto(user.getPhoto());
        articlePreview.setIntroduction(article.getIntroduction());
        articlePreview.setSource(article.getSource());
        articlePreview.setPublishTime(article.getPublishTime());
        articlePreview.setTitle(article.getTitle());

        return articlePreview;

    }

    public ArticleLikePreview toLikePreview(Article article, Date liketime){
        if(article==null){
            return null;
        }

        String userId = article.getUserId();
        User user = userRepository.getById(userId);

        ArticleLikePreview articlePreview = new ArticleLikePreview();
        articlePreview.setId(article.getId());
        articlePreview.setAuthorId(user.getId());
        articlePreview.setAuthor(user.getUsername());
        articlePreview.setAuthorPhoto(user.getPhoto());
        articlePreview.setIntroduction(article.getIntroduction());
        articlePreview.setSource(article.getSource());
        articlePreview.setPublishTime(article.getPublishTime());
        articlePreview.setTitle(article.getTitle());
        articlePreview.setLiketime(liketime);

        return articlePreview;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
