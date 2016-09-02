package com.shuntai.server;

import com.shuntai.mapper.UserMapper;
import com.shuntai.model.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hadoop on 2016/8/31.
 */

@Service
public class UserServer {

    @Autowired(required=true)
    private UserMapper userMapper;

    public UserBean getUser(){
        UserBean user=userMapper.getAllUsers();
        return user;
    }
}
