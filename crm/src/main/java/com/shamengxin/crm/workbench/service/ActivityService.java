package com.shamengxin.crm.workbench.service;

import com.shamengxin.crm.vo.PaginationVO;
import com.shamengxin.crm.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {
    Boolean save(Activity a);

    PaginationVO<Activity> pageList(Map<String, Object> map);

    boolean delete(String[] ids);
}
