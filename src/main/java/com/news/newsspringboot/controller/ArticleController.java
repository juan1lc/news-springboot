package com.news.newsspringboot.controller;

import com.news.newsspringboot.service.ArticleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
@CrossOrigin
public class ArticleController {

    ArticleService articleService;
}
