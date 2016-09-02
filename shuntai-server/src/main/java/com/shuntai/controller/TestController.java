package com.shuntai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hadoop on 2016/8/30.
 */


@Controller
public class TestController{



    @RequestMapping(value = "/")
    public String hello(){
        return "index";
    }


    @RequestMapping(value = "/test1")
    public String test1(){
        return "home";
    }


    @RequestMapping(value = "/checkUser", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        boolean isGet = request.getMethod().toLowerCase().equals("get");





    }

}
