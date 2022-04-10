package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.exception.BizException;
import com.news.newsspringboot.exception.ExceptionType;
import com.news.newsspringboot.model.dto.ArticleCommentCreateDto;
import com.news.newsspringboot.model.dto.PostCommentCreateDto;
import com.news.newsspringboot.model.entity.article.Article;
import com.news.newsspringboot.model.entity.article.ArticleComment;
import com.news.newsspringboot.model.entity.post.Post;
import com.news.newsspringboot.model.entity.post.PostComment;
import com.news.newsspringboot.model.mapper.ArticleCommentMapper;
import com.news.newsspringboot.model.mapper.PostCommentMapper;
import com.news.newsspringboot.repository.ArticleCommentRepository;
import com.news.newsspringboot.repository.ArticleRepository;
import com.news.newsspringboot.repository.PostCommentRepository;
import com.news.newsspringboot.repository.PostRepository;
import com.news.newsspringboot.service.CommentService;
import com.news.newsspringboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    PostCommentRepository repository;

    PostCommentMapper postCommentMapper;

    PostService postService;

    PostRepository postRepository;

    ArticleCommentRepository articleCommentRepository;

    ArticleCommentMapper articleCommentMapper;

    ArticleRepository articleRepository;

    @Override
    public PostComment createPostComment(PostCommentCreateDto postCommentCreateDto) {

        PostComment postComment = postCommentMapper.postCommentCreateEntity(postCommentCreateDto);

        Post post = postRepository.getById(postCommentCreateDto.getPostid());
        //草稿箱中的动态不能被评论
        checkPostStatus(post.getPoststatus());
        post.setCommentCount(post.getCommentCount()+1);
        postRepository.save(post);
        return repository.save(postComment);
    }

    @Override
    public ArticleComment createArticleComment(ArticleCommentCreateDto articleCommentCreateDto) {

        ArticleComment articleComment = articleCommentMapper.articleCommentCreateEntity(articleCommentCreateDto);

        Article article = articleRepository.getById(articleCommentCreateDto.getArticleid());
        article.setComment_count(article.getComment_count()+1);
        articleRepository.save(article);
        return articleCommentRepository.save(articleComment);
    }


    private void checkPostStatus(Integer poststatus) {
        if(poststatus!=0){
            throw new BizException(ExceptionType.POST_IS_A_DRAFT);
        }
    }

    @Override
    public void deletePostComment(String commentId) {
        PostComment postComment = repository.getById(commentId);
        String postId = postComment.getPostid();
        Post post = postRepository.getById(postId);
        post.setCommentCount(post.getCommentCount()-1);
        postRepository.save(post);
        repository.delete(postComment);
    }

    @Autowired
    public void setRepository(PostCommentRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setPostCommentMapper(PostCommentMapper postCommentMapper) {
        this.postCommentMapper = postCommentMapper;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Autowired
    public void setArticleCommentRepository(ArticleCommentRepository articleCommentRepository) {
        this.articleCommentRepository = articleCommentRepository;
    }

    @Autowired
    public void setArticleCommentMapper(ArticleCommentMapper articleCommentMapper) {
        this.articleCommentMapper = articleCommentMapper;
    }

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
}
