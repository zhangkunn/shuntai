package com.shuntai.server;

import com.alibaba.fastjson.JSON;
import com.shuntai.model.bean.ButtonType;
import com.shuntai.model.bean.WeixinButton;
import com.shuntai.model.bean.WeixinConf;
import com.shuntai.util.TokenManager;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.weixin4j.http.HttpsClient;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxahgkyh on 16/9/3.
 */

@Service
public class weixinMenuServer {

	public static final org.slf4j.Logger log = LoggerFactory.getLogger(weixinMenuServer.class);

	@Autowired
	private WeixinConf conf;



	public void initMenu() throws org.weixin4j.WeixinException {

		log.info(conf.getAppid() + "\t" + conf.getAppsecret());
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + TokenManager.getToken(conf);

        HttpsClient httpsClient = new HttpsClient();
        String responseData = httpsClient.post(url, getMenu()).asString();

        log.info("微信返回值为！" + responseData);
        if (!JSON.parseObject(responseData).get("errmsg").equals("ok")){
            httpsClient.post(url, getMenu());
            log.error("重新发起请求");
        }
	}








	public JSONObject getMenu(){

		Map<String, WeixinButton[]> map = new HashMap< String, WeixinButton[]>();
		map.put("button", new WeixinButton[]{
				new WeixinButton("菜单1",
						new WeixinButton("子菜单11", "https://atom.io/", ButtonType.view),
						new WeixinButton("子菜单12", "https://atom.io/", ButtonType.view),
						new WeixinButton("子菜单13", "https://atom.io/", ButtonType.view)),
				new WeixinButton("菜单2",
						new WeixinButton("子菜单21", "https://atom.io/", ButtonType.view),
						new WeixinButton("子菜单22", "https://atom.io/", ButtonType.view),
						new WeixinButton("子菜单23", "https://atom.io/", ButtonType.view)),
				new WeixinButton("菜单3",
						new WeixinButton("子菜单31", "https://atom.io/", ButtonType.view),
						new WeixinButton("子菜单32", "https://atom.io/", ButtonType.view),
						new WeixinButton("子菜单33", "https://atom.io/", ButtonType.view))
		});
		return JSONObject.fromObject(map);
	}



}
