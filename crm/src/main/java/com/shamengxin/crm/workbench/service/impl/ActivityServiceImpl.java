package com.shamengxin.crm.workbench.service.impl;

import com.shamengxin.crm.utils.SqlSessionUtil;
import com.shamengxin.crm.workbench.dao.ActivityDao;
import com.shamengxin.crm.workbench.domain.Activity;
import com.shamengxin.crm.workbench.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    public Boolean save(Activity a) {

        boolean flag=true;

        int count = activityDao.save(a);

        if (count != 1) {

            flag = false;

        }

        return flag;

    }
}
