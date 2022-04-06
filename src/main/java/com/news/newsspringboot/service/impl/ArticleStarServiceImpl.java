package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.entity.article.ArticleStar;
import com.news.newsspringboot.repository.ArticleRepository;
import com.news.newsspringboot.repository.ArticleStarRepository;
import com.news.newsspringboot.repository.UserRepository;
import com.news.newsspringboot.service.ArticleStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleStarServiceImpl implements ArticleStarService {
    ArticleStarRepository articleStarRepository;
    UserRepository userRepository;
    ArticleRepository articleRepository;

    @Override
    public boolean CheckStarTable(String articleid, String userid) {
        Optional<ArticleStar> stared = articleStarRepository.findByArticleidAndUserid(articleid, userid);
        return stared.isPresent();
    }

    @Override
    public Integer StarUnstar(String articleid, String userid) {
        boolean isStared = CheckStarTable(articleid, userid);
        if(isStared){
            ArticleStar articleStar = articleStarRepository.getArticleStarByArticleidAndUserid(articleid, userid);
            articleStarRepository.delete(articleStar);
            return 0;
        } else{
            starArticle(articleid, userid);
        }
        return 1;
    }

    private ArticleStar starArticle(String articleid, String userid) {
        ArticleStar articleStar = new ArticleStar();
        articleStar.setArticleid(articleid);
        articleStar.setUserid(userid);

        return articleStarRepository.save(articleStar);
    }

    @Autowired
    public void setArticleStarRepository(ArticleStarRepository articleStarRepository) {
        this.articleStarRepository = articleStarRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
}
