package com.news.newsspringboot.service;

public interface PostStarService {

    boolean checkStarTable(String postid, String userid);

    Integer StarUnstar(String postid, String userid);
}
