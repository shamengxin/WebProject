package com.shamengxin.crm.workbench.dao;

import com.shamengxin.crm.workbench.domain.ClueActivityRelation;

public interface ClueActivityRelationDao {


    int unbund(String id);

    int bund(ClueActivityRelation car);
}
