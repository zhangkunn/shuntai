package com.shuntai.mapper;

import com.shuntai.model.bean.UserBean;

import java.util.List;

/**
 * Created by hadoop on 2016/8/31.
 */

public interface UserMapper {

    public List<UserBean> getAllUsers();
}
