package com.shuntai.controller;

import com.shuntai.model.bean.UserBean;
import com.shuntai.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hadoop on 2016/8/31.
 */

@Controller
public class UserController {


    @Autowired(required=true)
    private UserServer userServer;


    @RequestMapping("/getAllUsers")
    @ResponseBody
    public List<UserBean> getUserInfo() {
        List<UserBean> user = userServer.getUser();
        if(user!=null){
            System.out.println("user.getName():"+user.toString());
        }
        return user;
    }

}
