package com.news.newsspringboot.repository;

import com.news.newsspringboot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 根据用户名准确的寻找用户
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 根据用户Id和密码返回用户
     * @param userId
     * @param password
     * @return
     */
    User getByIdAndPassword(String userId, String password);

    /**
     * 根据用户名准确的寻找用户
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);

    /**
     * 根据用户名和密码返回用户
     * @param username
     * @param password
     * @return
     */
    Optional<User> findUserByUsernameAndPassword(String username, String password);

    /**
     * 更换用户头像
     * @param id 用户ID
     * @param photo 头像的URL
     * @return 返回受影响的记录数
     */
    @Modifying
    @Query("update User user set user.photo=#{photo} where user.id=#{id}")
    void changePhoto(@Param("id") Integer id, @Param("photo") String photo);


}
