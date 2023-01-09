package com.shamengxin.crm.workbench.dao;

public interface ActivityRemarkDao {
    int getCountAids(String[] ids);

    int deleteByAids(String[] ids);
}
