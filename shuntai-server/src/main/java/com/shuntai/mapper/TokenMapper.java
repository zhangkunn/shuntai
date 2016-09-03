package com.shuntai.mapper;

import com.foxinmy.weixin4j.model.Token;

/**
 * Created by zxahgkyh on 16/9/3.
 */
public interface TokenMapper {

	public Token getToken();
	public void updateToken(Token token);
}
