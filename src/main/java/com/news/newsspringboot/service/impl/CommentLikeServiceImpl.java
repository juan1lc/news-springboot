package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.model.entity.article.ArticleComment;
import com.news.newsspringboot.model.entity.article.ArticleCommentLike;
import com.news.newsspringboot.model.entity.post.PostComment;
import com.news.newsspringboot.model.entity.post.PostCommentLike;
import com.news.newsspringboot.repository.ArticleCommentLikeRepository;
import com.news.newsspringboot.repository.ArticleCommentRepository;
import com.news.newsspringboot.repository.PostCommentLikeRepository;
import com.news.newsspringboot.repository.PostCommentRepository;
import com.news.newsspringboot.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {
    ArticleCommentLikeRepository commentLikeRepository;
    PostCommentLikeRepository postCommentLikeRepository;
    ArticleCommentRepository articleCommentRepository;
    PostCommentRepository postCommentRepository;

    @Override
    public boolean CheckALikeTable(String commentid, String userid) {
        Optional<ArticleCommentLike> liked = commentLikeRepository.findByUseridAndCommentid(userid, commentid);
        return liked.isPresent();
    }

    @Override
    public Integer LikeDisLikeAComment(String commentid, String userid) {
        boolean liked = CheckALikeTable(commentid, userid);
        ArticleComment articleComment = articleCommentRepository.getById(commentid);
        if(liked){
            ArticleCommentLike articleCommentLike = commentLikeRepository.getArticleCommentLikeByCommentidAndUserid(commentid, userid);
            commentLikeRepository.delete(articleCommentLike);
            articleComment.setCommentlike(articleComment.getCommentlike()-1);
            articleCommentRepository.save(articleComment);
            return 0;
        }else{
            ArticleCommentLike articleCommentLike = new ArticleCommentLike();
            articleCommentLike.setCommentid(commentid);
            articleCommentLike.setUserid(userid);
            commentLikeRepository.save(articleCommentLike);
            articleComment.setCommentlike(articleComment.getCommentlike()+1);
            articleCommentRepository.save(articleComment);
        }
        return 1;
    }

    @Override
    public Integer ACountLikeNum(String commentid) {
        List<ArticleCommentLike> articleCommentLikeList;
        articleCommentLikeList=commentLikeRepository.findAllByCommentid(commentid);

        return articleCommentLikeList.size();
    }

    @Override
    public boolean CheckPLikeTable(String commentid, String userid) {
        Optional<PostCommentLike> liked = postCommentLikeRepository.findByUseridAndCommentid(userid, commentid);
        return liked.isPresent();
    }

    @Override
    public Integer LikeDisLikePComment(String commentid, String userid) {
        boolean liked = CheckPLikeTable(commentid, userid);
        PostComment postComment = postCommentRepository.getById(commentid);
        if(liked){
            PostCommentLike postCommentLike = postCommentLikeRepository.getPostCommentLikeByCommentidAndUserid(commentid, userid);
            postCommentLikeRepository.delete(postCommentLike);
            postComment.setCommentlike(postComment.getCommentlike()-1);
            postCommentRepository.save(postComment);
            return 0;
        }else{
            PostCommentLike postCommentLike = new PostCommentLike();
            postCommentLike.setCommentid(commentid);
            postCommentLike.setUserid(userid);
            postCommentLikeRepository.save(postCommentLike);
            postComment.setCommentlike(postComment.getCommentlike()+1);
            postCommentRepository.save(postComment);
        }
        return 1;
    }

    @Override
    public Integer PCountLikeNum(String commentid) {
        List<PostCommentLike> postCommentLikeList;
        postCommentLikeList=postCommentLikeRepository.findAllByCommentid(commentid);

        return postCommentLikeList.size();
    }

    @Autowired
    public void setCommentLikeRepository(ArticleCommentLikeRepository commentLikeRepository) {
        this.commentLikeRepository = commentLikeRepository;
    }
    @Autowired
    public void setPostCommentLikeRepository(PostCommentLikeRepository postCommentLikeRepository) {
        this.postCommentLikeRepository = postCommentLikeRepository;
    }

    @Autowired
    public void setArticleCommentRepository(ArticleCommentRepository articleCommentRepository) {
        this.articleCommentRepository = articleCommentRepository;
    }

    @Autowired
    public void setPostCommentRepository(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }
}
