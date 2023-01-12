package com.shamengxin.crm.workbench.service.impl;

import com.shamengxin.crm.utils.SqlSessionUtil;
import com.shamengxin.crm.workbench.dao.ClueDao;
import com.shamengxin.crm.workbench.service.ClueService;

public class ClueServiceImpl implements ClueService {

    private ClueDao clueDao= SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);

}
