package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.model.entity.Article;
import com.news.newsspringboot.model.entity.ArticleLike;
import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.repository.ArticleLikeRepository;
import com.news.newsspringboot.repository.ArticleRepository;
import com.news.newsspringboot.repository.UserRepository;
import com.news.newsspringboot.service.ArticleLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleLikeServiceImpl implements ArticleLikeService {
    ArticleLikeRepository articleLikeRepository;
    UserRepository userRepository;
    ArticleRepository articleRepository;

    private ArticleLike likeArticle(String userid, String articleid) {
        User user = userRepository.getById(userid);
        Article article = articleRepository.getById(articleid);
        String username = user.getUsername();
        String avatar = user.getPhoto();

        ArticleLike articleLike = new ArticleLike();
        articleLike.setArticleid(articleid);
        articleLike.setPhoto(avatar);
        articleLike.setUserid(userid);
        articleLike.setUsername(username);

        return articleLikeRepository.save(articleLike);
    }

    @Override
    public boolean CheckLikeTable(String articleId, String userId) {
        Optional<ArticleLike> liked = articleLikeRepository.findByArticleidAndUserid(articleId, userId);
        return liked.isPresent();
    }

    @Override
    public List<ArticleLike> getAllLikers(String articleid) {
        return articleLikeRepository.findAllByArticleid(articleid);
    }

    @Override
    public Integer LikeDislkeArticle(String articleid, String userid) {
        Article article = articleRepository.getById(articleid);
        Integer likeNum = article.getArticle_like();
        boolean isLiked = CheckLikeTable(articleid, userid);
        if(isLiked){
            ArticleLike articleLike = articleLikeRepository.getArticleLikeByArticleidAndUserid(articleid, userid);
            articleLikeRepository.delete(articleLike);
            likeNum--;
        }else{
            likeArticle(userid, articleid);
            likeNum++;
        }
        article.setArticle_like(likeNum);
        articleRepository.save(article);
        return likeNum;
    }

    @Autowired
    public void setArticleLikeRepository(ArticleLikeRepository articleLikeRepository) {
        this.articleLikeRepository = articleLikeRepository;
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
