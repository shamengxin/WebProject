package com.shamengxin.crm.workbench.service.impl;

import com.shamengxin.crm.utils.SqlSessionUtil;
import com.shamengxin.crm.utils.UUIDUtil;
import com.shamengxin.crm.workbench.dao.ClueActivityRelationDao;
import com.shamengxin.crm.workbench.dao.ClueDao;
import com.shamengxin.crm.workbench.domain.Clue;
import com.shamengxin.crm.workbench.domain.ClueActivityRelation;
import com.shamengxin.crm.workbench.service.ClueService;

public class ClueServiceImpl implements ClueService {

    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
    private ClueActivityRelationDao clueActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);

    public boolean save(Clue clue) {

        boolean flag = true;

        int count = clueDao.save(clue);

        if (count != 1) {

            flag = false;
        }

        return flag;
    }

    public Clue detail(String id) {

        Clue c = clueDao.detail(id);

        return c;
    }

    public boolean unbund(String id) {

        boolean flag = true;

        int count = clueActivityRelationDao.unbund(id);

        if(count!=1){

            flag=false;

        }

        return flag;
    }

    public boolean bund(String cid, String[] aids) {

        boolean flag = true;

        for(String aid:aids){

            //取得每一个aid和cid做关联
            ClueActivityRelation car = new ClueActivityRelation();

            car.setId(UUIDUtil.getUUID());
            car.setClueId(cid);
            car.setActivityId(aid);

            //添加关联关系表中的记录
            int count = clueActivityRelationDao.bund(car);

            if(count!=1){

                flag=false;

            }

        }

        return flag;
    }
}
