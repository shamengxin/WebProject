package com.shamengxin.crm.workbench.dao;

import com.shamengxin.crm.workbench.domain.Tran;

public interface TranDao {

    int save(Tran t);

    Tran detail(String id);
}
