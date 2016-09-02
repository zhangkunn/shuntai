package com.shuntai.controller;

import com.templates.model.bean.UserBean;
import com.templates.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hadoop on 2016/8/31.
 */

@Controller
public class UserController {


    @Autowired(required=true)
    private UserServer userServer;


    @RequestMapping("/getAllUsers")
    @ResponseBody
    public UserBean getUserInfo() {
        UserBean user = userServer.getUser();
        if(user!=null){
            System.out.println("user.getName():"+user.getName());
        }
        return user;
    }

}
