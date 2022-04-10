package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.model.entity.article.ArticleHistory;
import com.news.newsspringboot.model.entity.post.PostHistory;
import com.news.newsspringboot.model.mapper.ArticleMapper;
import com.news.newsspringboot.model.mapper.PostMapper;
import com.news.newsspringboot.model.vo.ArticleHistoryVo;
import com.news.newsspringboot.model.vo.PostHistoryVo;
import com.news.newsspringboot.repository.ArticleHistoryRepository;
import com.news.newsspringboot.repository.PostHistoryRepository;
import com.news.newsspringboot.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {
    ArticleHistoryRepository repository;
    ArticleMapper articleMapper;
    PostHistoryRepository postHistoryRepository;
    PostMapper postMapper;

    @Override
    public ArticleHistory create(String userid, String articleid) {
        Optional<ArticleHistory> history = repository.findByArticleidAndUserid(articleid, userid);
        if(history.isPresent()){
            ArticleHistory articleHistory = repository.getArticleHistoryByArticleidAndUserid(articleid, userid);
            articleHistory.setTime(new Date());
            return  repository.save(articleHistory);
        }else{
            ArticleHistory newhistory = new ArticleHistory();
            newhistory.setArticleid(articleid);
            newhistory.setUserid(userid);
            return repository.save(newhistory);
        }
    }

    @Override
    public List<ArticleHistoryVo> getArticleHistoryVos(String userid) {
        List<ArticleHistory> articleHistories = repository.findAllByUseridOrderByTimeDesc(userid);
        List<ArticleHistoryVo> vos = new ArrayList<>();
        for(ArticleHistory articleHistory:articleHistories){
            vos.add(articleMapper.toHistoryVo(articleHistory));
        }
        return vos;
    }

    @Override
    public PostHistory createPostHistory(String userid, String postid) {
        Optional<PostHistory> history = postHistoryRepository.findByUseridAndPostid(userid, postid);
        if(history.isPresent()){
            PostHistory postHistory = postHistoryRepository.getPostHistoryByUseridAndPostid(userid, postid);
            postHistory.setTime(new Date());
            return  postHistoryRepository.save(postHistory);
        }else{
            PostHistory newhistory = new PostHistory();
            newhistory.setPostid(postid);
            newhistory.setUserid(userid);
            return postHistoryRepository.save(newhistory);
        }
    }

    @Override
    public List<PostHistoryVo> getPostHistoryVos(String userid) {
        List<PostHistory> postHistories = postHistoryRepository.findAllByUseridOrderByTimeDesc(userid);
        List<PostHistoryVo> vos = new ArrayList<>();
        for(PostHistory postHistory:postHistories){
            vos.add(postMapper.toHistoryVo(postHistory));
        }
        return vos;
    }

    @Autowired
    public void setRepository(ArticleHistoryRepository repository) {
        this.repository = repository;
    }
    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }
    @Autowired
    public void setPostHistoryRepository(PostHistoryRepository postHistoryRepository) {
        this.postHistoryRepository = postHistoryRepository;
    }
    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }
}
