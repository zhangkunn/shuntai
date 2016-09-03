package com.shuntai.util;

import com.foxinmy.weixin4j.model.Token;
import com.shuntai.mapper.TokenMapper;
import com.shuntai.model.bean.WeixinConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.http.OAuthToken;

import java.util.Hashtable;

/**
 * Created by zxahgkyh on 16/9/3.
 */

public class TokenManager {
	public static final Logger log = LoggerFactory.getLogger(TokenManager.class);

	static Hashtable<String, OAuthToken> token = new Hashtable();
	private static  Weixin weixin = new Weixin();


//	@Autowired(required=true)
//	private TokenMapper tokenMapper;

	public static String getToken(WeixinConf conf) throws WeixinException {


		if (token.isEmpty() || isExpired()){

			token.put("token", weixin.login(conf.getAppid(), conf.getAppsecret()));
			log.info("申请新的token ：" + token.get("token").toString());
		}
		return token.get("token").getAccess_token();
	}



	public static Boolean isExpired(){
		return !(token.get("token").getCreate_time() + 7200l > System.currentTimeMillis());
	}
}
