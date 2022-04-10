package com.news.newsspringboot.controller;

import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.model.vo.*;
import com.news.newsspringboot.service.ArticleService;
import com.news.newsspringboot.service.PostService;
import com.news.newsspringboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/users")
@Api(tags = "用户")
public class UserController {

    UserService userService;
    ArticleService articleService;
    PostService postService;

    @GetMapping
    @ApiOperation("用户检索")
    @RolesAllowed({"ROLE_ADMIN"})
    Page<User> search(@PageableDefault(sort = {"username"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.search(pageable);
    }

    @PostMapping
    User create(@Validated User user) {
        return userService.createUser(user);
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=utf-8")
    User get(@PathVariable String id) {
        return userService.getUserById(id);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(@PathVariable String id) {
        userService.deleteUser(id);
    }


    @GetMapping(value = "info/{id}", produces = "application/json;charset=utf-8")
    Response getUserInfoById(@PathVariable("id") String id){
        log.info("#### 获取指定用户的信息，入参：userId={}",id);
        User user = userService.getUserById(id);
        log.info("#### 获取指定用户的信息，结果：user={}",user);
        if(Objects.isNull(user)){
            return Response.responseFail("用户ID无效",null);
        }
        return Response.responseSuccess("获取成功",user);
    }

    @PutMapping("info/edit")
    Response editUserInfo(@RequestParam(value="id") String id,
                          @RequestParam(value="columnName") String columnName,
                          @RequestParam(value="columnValue") String columnValue){
        log.info("#### 用户接口API，入参：id={}，columnName={}，columnValue={}"
                ,id,columnName,columnValue);
        // 更新的用户信息
        boolean b = false;
        if (columnName.equals("userName")) {
            userService.editUserName(id, columnValue);
            b=true;
        }
        if (columnName.equals("phone")) {
            userService.editPhone(id, columnValue);
            b=true;
        }
        if (columnName.equals("mail")) {
            userService.editMail(id, columnValue);
            b=true;
        }
        if(columnName.equals("introduction")){
            userService.editUserIntro(id, columnValue);
            b=true;
        }
        if(columnName.equals("gender")){
            userService.editUserGender(id, columnValue);
            b=true;
        }
        if(columnName.equals("birth")){
            userService.editBirth(id, columnValue);
            b=true;
        }
        if(columnName.equals("address")){
            userService.editAdress(id, columnValue);
            b=true;
        }
        if(!b){
            return Response.responseFail("修改信息失败！",null);
        }
        // 查询用户信息，刷新数据
        final User updateUser = userService.getUserById(id);
        log.info("#### 用户接口API，修改用户信息，结果：updateUser={}",updateUser);
        return Response.responseSuccess("修改信息成功！",Objects.isNull(updateUser) ? null: updateUser);
    }


    @PutMapping("/safe/password/modify")
    Response modifyUserPassword(@RequestParam("userName") String userName,@RequestParam("password") String password,
                                @RequestParam("newPassword") String newPassword){
        log.info("#### 用户密码修改API，入参：userName={}，password={}，newPassword={}",userName,password,newPassword);
        boolean  b = userService.modifyUserPassword(userName, password, newPassword);
        if(!b){
            log.info("#### 用户密码修改API，修改密码失败！");
            return Response.responseFail("修改失败！",null);
        }
        log.info("#### 用户密码修改API，修改密码成功！");
        return Response.responseSuccess("修改成功！",null);
    }


    @PostMapping(value = "/info/editphoto/{id}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    Response changeUserAvatar(@PathVariable("id") String id, MultipartFile file){
        log.info("#### 用户头像修改，入参：userId={}，file={}",id,file);
        final String photo = userService.changePhoto(id, file);
        if(Objects.isNull(photo)){
            log.info("#### 用户头像修改，用户头像上传失败！");
            return Response.responseFail("头像上传失败！",null);
        }
        log.info("#### 用户头像修改，用户头像上传成功！");
        return Response.responseSuccess("头像上传成功！",photo);
    }

    @GetMapping(value = "/like-article-list/{id}", produces = "application/json;charset=utf-8")
    List<ArticleLikePreview> getLikedArticle(@PathVariable("id") String id){
        return articleService.getUserLike(id);
    }

    @GetMapping(value = "/star-article-list/{id}", produces = "application/json;charset=utf-8")
    List<ArticleLikePreview> getStaredArticle(@PathVariable("id") String id){
        return articleService.getUserStar(id);
    }

    @GetMapping(value = "/like-post-list/{id}", produces = "application/json;charset=utf-8")
    List<PostLikePreview> getLikedPost(@PathVariable("id") String id){
        return postService.getUserLike(id);
    }

    @GetMapping(value = "/star-post-list/{id}", produces = "application/json;charset=utf-8")
    List<PostLikePreview> getStaredPost(@PathVariable("id") String id){
        return postService.getUserStar(id);
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}

