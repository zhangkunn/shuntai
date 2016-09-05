package com.shuntai.model.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zxahgkyh on 16/9/3.
 */
public class WeixinButton {
	private static final long serialVersionUID = -6422234732203854866L;
	private String name;
	private ButtonType type;
	private String url;
	@JSONField(
			serialize = false,
			deserialize = false
	)
	private Object extra;
	@JSONField(
			name = "sub_button"
	)
	private List<WeixinButton> subs;

	protected WeixinButton() {
		this.subs = new ArrayList();
	}

	public WeixinButton(String name, WeixinButton... subButtons) {
		this.name = name;
		this.subs = Arrays.asList(subButtons);
	}

	public WeixinButton(String name, String content, ButtonType type) {
		this.name = name;
		this.url = content;
		this.type = type;
		this.subs = new ArrayList();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ButtonType getType() {
		return this.type;
	}

	public void setType(ButtonType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getExtra() {
		return this.extra;
	}

	public void setExtra(Object extra) {
		this.extra = extra;
	}

	public List<WeixinButton> getSubs() {
		return this.subs;
	}

	public void setSubs(List<WeixinButton> subs) {
		this.subs = subs;
	}

	public WeixinButton pushSub(WeixinButton btn) {
		this.subs.add(btn);
		return this;
	}

	public String toString() {
		return "Button [name=" + this.name + ", type=" + this.type + ", content=" + this.url + ", extra=" + this.extra + ", subs=" + this.subs + "]";
	}





}
