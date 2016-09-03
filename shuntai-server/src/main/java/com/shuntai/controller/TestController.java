package com.shuntai.controller;

import com.shuntai.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hadoop on 2016/8/30.
 */


@Controller
public class TestController{

    public static final Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/")
    public String hello(){
        return "index";
    }


    @RequestMapping(value = "/test1")
    public String test1(){
        return "home";
    }


    @RequestMapping(value = "/checkWeixin", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isGet = request.getMethod().toLowerCase().equals("get");

        log.info("客户ip是：" + request.getRemoteAddr());

        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }else
            out.print("err");
        out.close();

    }

}
