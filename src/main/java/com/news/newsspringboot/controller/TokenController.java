package com.news.newsspringboot.controller;

import com.news.newsspringboot.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenController {


    UserService userService;


}
