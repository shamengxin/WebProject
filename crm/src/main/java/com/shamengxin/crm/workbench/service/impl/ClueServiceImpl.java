package com.shamengxin.crm.workbench.service.impl;

import com.shamengxin.crm.utils.DateTimeUtil;
import com.shamengxin.crm.utils.SqlSessionUtil;
import com.shamengxin.crm.utils.UUIDUtil;
import com.shamengxin.crm.workbench.dao.*;
import com.shamengxin.crm.workbench.domain.Clue;
import com.shamengxin.crm.workbench.domain.ClueActivityRelation;
import com.shamengxin.crm.workbench.domain.Tran;
import com.shamengxin.crm.workbench.service.ClueService;

public class ClueServiceImpl implements ClueService {

    //线索相关的表
    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
    private ClueActivityRelationDao clueActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);
    private ClueRemarkDao clueRemarkDao= SqlSessionUtil.getSqlSession().getMapper(ClueRemarkDao.class);

    //客户相关表
    private CustomerDao customerDao= SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    private CustomerRemarkDao customerRemarkDao= SqlSessionUtil.getSqlSession().getMapper(CustomerRemarkDao.class);

    //联系人相关表
    private ContactsDao contactsDao= SqlSessionUtil.getSqlSession().getMapper(ContactsDao.class);
    private ContactsRemarkDao contactsRemarkDao= SqlSessionUtil.getSqlSession().getMapper(ContactsRemarkDao.class);
    private ContactsActivityRelationDao contactsActivityRelationDao= SqlSessionUtil.getSqlSession().getMapper(ContactsActivityRelationDao.class);

    //交易相关表
    private TranDao tranDao= SqlSessionUtil.getSqlSession().getMapper(TranDao.class);
    private TranHistoryDao tranHistoryDao= SqlSessionUtil.getSqlSession().getMapper(TranHistoryDao.class);

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

    public boolean convert(String clueId, Tran t, String createBy) {

        String createTime = DateTimeUtil.getSysTime();

        boolean flag = true;



        return flag;
    }


}
