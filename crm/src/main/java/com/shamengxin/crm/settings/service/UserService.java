package com.shamengxin.crm.settings.service;

import com.shamengxin.crm.exception.LoginException;
import com.shamengxin.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
