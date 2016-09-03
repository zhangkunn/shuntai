package com.shuntai.server;

import com.alibaba.fastjson.JSON;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.WeixinRequestExecutor;
import com.foxinmy.weixin4j.http.weixin.WeixinResponse;
import com.foxinmy.weixin4j.type.ButtonType;
import com.shuntai.model.bean.WeixinButton;
import com.shuntai.model.bean.WeixinConf;
import com.shuntai.util.TokenManager;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxahgkyh on 16/9/3.
 */

@Service
public class WeixinMenuServer {

	public static final org.slf4j.Logger log = LoggerFactory.getLogger(WeixinMenuServer.class);

	@Autowired
	private WeixinConf conf;


	@PostConstruct
	public void initMenu() throws WeixinException, org.weixin4j.WeixinException {
		System.err.println("dfjhdskfhskfgkjsfgsdkjfg-------------");
		log.info(conf.getAppid() + "\t" + conf.getAppsecret());
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + TokenManager.getToken(conf);
		String param = getMenu();
		WeixinRequestExecutor weixinRequestExecutor = new WeixinRequestExecutor();

		WeixinResponse weixinResponse = weixinRequestExecutor.post(url,param);



		String result = "";
		BufferedReader in = null;
		StringBuilder str = new StringBuilder();
		try {
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(weixinResponse.getBody()));
			String line;
			while ((line = in.readLine()) != null) {
				str.append(line);
			}
			log.error("微信返回值为！" + str.toString());
			if (!JSON.parseObject(str.toString()).get("errcode").equals("0")){
				weixinRequestExecutor.post(url,param);
				log.error("重新发起请求");
			}

		} catch (Exception e) {


			log.error("发送 POST 请求出现异常！" + e);
		}
		//使用finally块来关闭输出流、输入流
		finally{
			try{
				if(in!=null){
					in.close();
				}
			}
			catch(IOException ex){
				log.error("关闭流出现异常！" + ex);
			}
		}



	}








	public String getMenu(){

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
		return JSON.toJSONString(map);
	}

}
