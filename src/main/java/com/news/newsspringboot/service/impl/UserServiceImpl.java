package com.news.newsspringboot.service.impl;

import com.news.newsspringboot.model.entity.User;
import com.news.newsspringboot.exception.BizException;
import com.news.newsspringboot.exception.ExceptionType;
import com.news.newsspringboot.repository.UserRepository;
import com.news.newsspringboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static com.news.newsspringboot.utils.FileUploadUtil.singleFileUpload;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        log.info("#### 用户服务层，创建用户：user={}",user);
        checkUserName(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    private void checkUserName(String userName){
        log.info("#### 用户服务层，检查用户名唯一性：userName={}",userName);
        Optional<User> user = repository.findByUsername(userName);
        if(user.isPresent()){
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
    }

    @Override
    public void deleteUser(String userId) {

        repository.delete(getUserById(userId));
    }

//    @Override
//    public Map<String, Object> getInfoPageAllCounts(Integer userId) {
//        log.info("#### 用户服务层，入参：userId={}",userId);
//        List<Map<String, Object>> countsMapList = userMapper.selectCounts(userId);
//        log.info("#### 用户服务层，查询原始结果：countsMapList={}",countsMapList);
//        if(CollectionUtils.isEmpty(countsMapList)){
//            return null;
//        }
//        // 取出原始的数据进行业务数据的包装
//        Map<String,Object> countMap = new HashMap<>(3);
//        // 动态计数项和获赞数
//        countMap.putAll(countsMapList.get(0));
//        // @
//        countMap.put("atCount",countsMapList.get(1).get("likeCount"));
//        log.info("#### 用户服务层，获取用户计数项，计数集合：countMap={}",countMap);
//        return countMap;
//    }

    @Transactional
    @Override
    public int editUserName(String userId, String columnValue) {

        User preuser = repository.getById(userId);
        preuser.setUsername(columnValue);
        repository.save(preuser);
        return 0;

    }

    @Override
    public int editPassword(String userId, String columnValue) {
        User preuser = repository.getById(userId);
        preuser.setPassword(columnValue);
        repository.save(preuser);
        return 0;
    }

    @Override
    public int editPhone(String userId, String columnValue) {

        User preuser = repository.getById(userId);
        preuser.setPhone(columnValue);
        repository.save(preuser);
        return 0;
    }

    @Override
    public int editMail(String userId, String columnValue) {

        User preuser = repository.getById(userId);
        preuser.setMail(columnValue);
        repository.save(preuser);
        return 0;
    }

    @Override
    public int editUserIntro(String userId, String columnValue) {
        User preuser = repository.getById(userId);
        preuser.setIntroduction(columnValue);
        repository.save(preuser);
        return 0;
    }

    @Override
    public String changePhoto(String userId, MultipartFile file) {
        String fileName = singleFileUpload(file);
        User preUser=getUserById(userId);
        preUser.setPhoto(fileName);
        repository.save(preUser);
        return fileName;
    }

    @Override
    public boolean modifyUserPassword(String userName, String password, String newPassword) {
        if(findUserByUsername(userName,password)!=null) {
            User user = findUserByUsername(userName,password);
            user.setPassword(passwordEncoder.encode(newPassword));
            repository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(String userId) {

        return repository.getById(userId);
    }

    @Override
    public User getUserByUsername(String userName) {

        return repository.getUserByUsername(userName);
    }

    @Override
    public User findUser(String userId, String password) {

        return repository.getByIdAndPassword(userId, password);
    }

    @Override
    public User findUserByUsername(String userName, String password) {
        User user = getUserByUsername(userName);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }
        return user;
    }

    @Override
    public Page<User> search(Pageable pageable) {

        return repository.findAll(pageable);
    }

}
