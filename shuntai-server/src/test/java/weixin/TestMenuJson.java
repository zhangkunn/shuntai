package weixin;

import com.alibaba.fastjson.JSON;
import com.shuntai.model.bean.ButtonType;
import com.shuntai.model.bean.WeixinButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxahgkyh on 16/9/3.
 */
public class TestMenuJson {


	public static void main(String[] args) {

		TestMenuJson tmj = new TestMenuJson();

		System.out.println(tmj.getMenu());

	}



	public String getMenu(){

		Map<String, WeixinButton[]> map = new HashMap< String, WeixinButton[]>();
		map.put("button", new WeixinButton[]{
				new WeixinButton("菜单1",
						new WeixinButton("子菜单11", "https://atom.io/", ButtonType.view),
						new WeixinButton("子菜单13", "https://atom.io/", ButtonType.view)),
				new WeixinButton("菜单2",
						new WeixinButton("子菜单21", "https://atom.io/", ButtonType.view),
						new WeixinButton("子菜单23", "https://atom.io/", ButtonType.view))
		});
		return JSON.toJSONString(map);



	}
}
