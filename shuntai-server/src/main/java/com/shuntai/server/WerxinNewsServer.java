package com.shuntai.server;

import com.shuntai.util.SignUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.weixin4j.message.InputMessage;
import org.weixin4j.message.MsgType;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hadoop on 2016/9/5.
 */
@Service
public class WerxinNewsServer {
    public static final org.slf4j.Logger log = LoggerFactory.getLogger(WerxinNewsServer.class);



    public void respondGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
            log.info("验证通过，原样返回echostr=" + echostr);
        }else{
            out.print("err");
            log.error("验证失败，返回err");
        }
        out.close();
    }

    public void respondPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //处理接收消息
        ServletInputStream in = request.getInputStream();
        //将POST流转换为XStream对象
        XStream xs = new XStream(new DomDriver());
        //将指定节点下的xml节点数据映射为对象
        xs.alias("xml", InputMessage.class);
        //将流转换为字符串
        StringBuilder xmlMsg = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            xmlMsg.append(new String(b, 0, n, "UTF-8"));
        }
        //将xml内容转换为InputMessage对象
        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());
        // 取得消息类型
        String msgType = inputMsg.getMsgType();

        if (msgType.equals(MsgType.Text.toString())) {
            //文本消息
            System.out.println("开发者微信号：" + inputMsg.getToUserName());
            System.out.println("发送方帐号：" + inputMsg.getFromUserName());
            System.out.println("消息创建时间：" + inputMsg.getCreateTime());
            System.out.println("消息内容：" + inputMsg.getContent());
            System.out.println("消息Id：" + inputMsg.getMsgId());
        }

        PrintWriter out = response.getWriter();
        //测试，原样返回文本信息
        out.write(inputMsg.getContent());
        out.close();
    }





}
