package com.shuntai.model.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by zxahgkyh on 16/9/3.
 */
@ConfigurationProperties(prefix = "spring.weixin", locations = "classpath:weixinconf.properties")
public class WeixinConf {

	private String appid;
	private String appsecret;


	public String getAppid() {
		return appid;
	}

	public String getAppsecret() {
		return appsecret;
	}


	public void setAppid(String appid) {
		this.appid = appid;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
}
