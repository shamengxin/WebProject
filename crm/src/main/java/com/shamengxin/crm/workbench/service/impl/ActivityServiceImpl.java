package com.shamengxin.crm.workbench.service.impl;

import com.shamengxin.crm.settings.dao.UserDao;
import com.shamengxin.crm.settings.domain.User;
import com.shamengxin.crm.utils.SqlSessionUtil;
import com.shamengxin.crm.vo.PaginationVO;
import com.shamengxin.crm.workbench.dao.ActivityDao;
import com.shamengxin.crm.workbench.dao.ActivityRemarkDao;
import com.shamengxin.crm.workbench.domain.Activity;
import com.shamengxin.crm.workbench.domain.ActivityRemark;
import com.shamengxin.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);


    public Boolean save(Activity a) {

        boolean flag = true;

        int count = activityDao.save(a);

        if (count != 1) {

            flag = false;

        }

        return flag;

    }

    public PaginationVO<Activity> pageList(Map<String, Object> map) {

        //取得total
        int total = activityDao.getTotalByCondition(map);

        //取得datalist
        List<Activity> dataList = activityDao.getActivityListByCondition(map);

        //创建一个vo对象，将total和datalist封装到vo中
        PaginationVO<Activity> vo = new PaginationVO<Activity>();
        vo.setTotal(total);
        vo.setDataList(dataList);

        //将vo返回
        return vo;
    }

    public boolean delete(String[] ids) {

        boolean flag = true;

        //先查询出需要删除的备注的数量
        int count1 = activityRemarkDao.getCountAids(ids);

        //删除备注，返回受到影响的条数（实际删除的数量）
        int count2 = activityRemarkDao.deleteByAids(ids);

        if (count1 != count2) {

            flag = false;

        }

        //删除市场活动
        int count3 = activityDao.delete(ids);

        if (count3 != ids.length) {

            flag = false;

        }

        return flag;
    }

    public Map<String, Object> getUserListAndActivity(String id) {

        //取uList
        List<User> uList = userDao.getUserList();

        //取a
        Activity a = activityDao.getById(id);

        //将uList和a打包到map中
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uList", uList);
        map.put("a", a);

        //返回map就可以了
        return map;
    }

    public Boolean update(Activity a) {
        boolean flag = true;

        int count = activityDao.update(a);

        if (count != 1) {

            flag = false;

        }

        return flag;
    }

    public Activity detail(String id) {

        Activity a = activityDao.detail(id);

        return a;

    }

    public List<ActivityRemark> getRemarkListByAid(String activityId) {

        List<ActivityRemark> arList=activityRemarkDao.getRemarkListByAid(activityId);

        return arList;

    }

    public boolean deleteRemark(String id) {
        boolean flag =true;

        int count =activityRemarkDao.deleteById(id);

        if(count!=1){

            flag=false;

        }

        return flag;
    }

    public boolean saveRemark(ActivityRemark ar) {

        boolean flag=true;

        int count =activityRemarkDao.saveRemark(ar);

        if(count!=1){

            flag=false;

        }

        return flag;

    }

    public boolean updateRemark(ActivityRemark ar) {
        boolean flag=true;

        int count =activityRemarkDao.updateRemark(ar);

        if(count!=1){

            flag=false;

        }

        return flag;
    }

    public List<Activity> getActivityListByClueId(String clueId) {

        List<Activity> aList = activityDao.getActivityListByClueId(clueId);

        return aList;

    }

    public List<Activity> getActivityListByNameAndNotByClueId(Map<String, String> map) {

        List<Activity> aList = activityDao.getActivityListByNameAndNotByClueId(map);

        return aList;
    }

    public List<Activity> getActivityListByName(String aname) {

        List<Activity> aList = activityDao.getActivityListByName(aname);

        return aList;
    }

}
