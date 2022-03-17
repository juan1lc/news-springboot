package com.news.newsspringboot.service;

import com.news.newsspringboot.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    //新增用户
    User createUser(User user);
    //删除用户
    void deleteUser(String userId);

//    /**
//     * 根据 userId 获取获赞，@我，动态计数
//     * @param userId 用户ID
//     * @return 返回计数的Map集合
//     */
//    Map<String, Object> getInfoPageAllCounts(Integer userId);

    //更换用户头像
    String changePhoto(String userId, MultipartFile file);
    //用户密码修改
    boolean modifyUserPassword(String userName, String password, String newPassword);
    //修改用户信息
    int editUserName(String userId, String columnValue);
    int editPassword(String userId, String columnValue);
    int editPhone(String userId, String columnValue);
    int editMail(String userId, String columnValue);
    int editUserIntro(String userId, String columnValue);
    //查询用户信息
    User getUserById(String userId);
    //身份验证
    User getUserByUsername(String userName);
    User findUser(String userId,String password);
    User findUserByUsername(String userName, String password);
    //查询所有用户信息
    Page<User> search(Pageable pageable);
}
