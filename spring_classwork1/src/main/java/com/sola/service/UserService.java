package com.sola.service;

import com.sola.mapper.UserMapper;
import com.sola.pojo.Classwork1User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper; // 引用接口

    public UserMapper getMapper() {
        return userMapper;
    }
    
    public void setMapper(UserMapper mapper) {
        this.userMapper = mapper;
    }

    public List<Classwork1User> show() {
        return userMapper.selectAll();
    }

}
