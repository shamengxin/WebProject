package com.shamengxin.crm.settings.service.impl;

import com.shamengxin.crm.settings.dao.UserDao;
import com.shamengxin.crm.settings.service.UserService;
import com.shamengxin.crm.utils.SqlSessionUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

}
