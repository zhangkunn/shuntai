package com.shuntai.controller;

import com.shuntai.server.WerxinNewsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hadoop on 2016/8/30.
 */


@Controller
public class TestController{

    public static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired(required = true)
    private WerxinNewsServer werxinNewsServer;

    @RequestMapping(value = "/")
    public String hello(){
        return "index";
    }
    @RequestMapping(value = "/index")
    public String hello1(){
        return "index";
    }
    @RequestMapping(value = "/index.html")
    public String hello2(){
        return "index";
    }
    @RequestMapping(value = "/my")
    public String my(){
        return "my";
    }

    @RequestMapping(value = "/home")
    public String test1(){
        return "home";
    }







    @RequestMapping(value = "/checkWeixin", method = {RequestMethod.GET,RequestMethod.POST})
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String methodType = request.getMethod().toLowerCase();
        switch (methodType){
            case "get": werxinNewsServer.respondGet(request, response);
                break;
            case "post" : werxinNewsServer.respondPost(request, response);
                break;
            default: break;
        }

    }

}
