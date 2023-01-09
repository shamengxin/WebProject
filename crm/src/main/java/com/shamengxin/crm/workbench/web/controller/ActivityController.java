package com.shamengxin.crm.workbench.web.controller;

import com.shamengxin.crm.settings.domain.User;
import com.shamengxin.crm.settings.service.UserService;
import com.shamengxin.crm.settings.service.impl.UserServiceImpl;
import com.shamengxin.crm.utils.*;
import com.shamengxin.crm.vo.PaginationVO;
import com.shamengxin.crm.workbench.domain.Activity;
import com.shamengxin.crm.workbench.service.ActivityService;
import com.shamengxin.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ActivityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("进入到市场活动控制器");

        String path = request.getServletPath();

        if ("/workbench/activity/getUserList.do".equals(path)) {

            getUserList(request, response);

        } else if ("/workbench/activity/save.do".equals(path)) {

            save(request, response);

        } else if ("/workbench/activity/pageList.do".equals(path)) {

            pageList(request, response);

        } else if ("/workbench/activity/delete.do".equals(path)) {

            delete(request, response);

        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行市场活动的删除操作");

        String[] ids = request.getParameterValues("id");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.delete(ids);

        PrintJson.printJsonFlag(response,flag);

    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("进入到查询市场活动信息列表的操作（结合条件查询以及分页查询)");
        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNOStr = request.getParameter("pageNO");
        int pageNO = Integer.valueOf(pageNOStr);
        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        //计算出略过的记录数
        int skipCount = (pageNO - 1) * pageSize;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("skipCount", skipCount);
        map.put("pageSize", pageSize);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        /*

           前端要：市场活动信息表
                  查询总条数

                  业务层拿到了以上两项信息后，如果做返回
                  map
                  map.put("dataList":dataList)
                  map.put("total":total)
                  PrintJSON map-->json
                  {"total":100,"dataList":[{市场活动1},{2},{3}]}

                  vo
                  paginationVO<T>
                    private int total;
                    private List<T> dataList;

                  paginationVO<Activity> vo = new paginationVO<>();
                  vo.setTotal(total);
                  bo.setDataList(dataList);
                  PrintJSON vo-->json
                  {"total":100,"dataList":[{市场活动1},{2},{3}]}
                  将来分页查询，每个模块都有，所以我们选择使用一个通用的vo，操作起来比较方便


         */

        PaginationVO<Activity> vo = as.pageList(map);

        //vo-->{"total":100,"dataList":[{市场活动1},{2},{3}]}
        PrintJson.printJsonObj(response, vo);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行市场活动添加操作");

        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        //创建时间：当前系统时间
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User) request.getSession().getAttribute("user")).getName();

        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        Boolean flag = as.save(a);

        PrintJson.printJsonFlag(response, flag);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("取得用户信息列表");

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        List<User> uList = us.getUserList();

        PrintJson.printJsonObj(response, uList);

    }

}
