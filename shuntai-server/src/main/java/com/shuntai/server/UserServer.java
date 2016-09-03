package com.shuntai.server;

import com.shuntai.mapper.UserMapper;
import com.shuntai.model.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hadoop on 2016/8/31.
 */

@Service
public class UserServer {

    @Autowired(required=true)
    private UserMapper userMapper;

    public List<UserBean> getUser(){
        List<UserBean> user=userMapper.getAllUsers();
        return user;
    }
}
