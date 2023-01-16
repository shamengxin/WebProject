package com.shamengxin.crm.workbench.service.impl;

import com.shamengxin.crm.utils.SqlSessionUtil;
import com.shamengxin.crm.workbench.dao.CustomerDao;
import com.shamengxin.crm.workbench.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);


    public List<String> getCustomerName(String name) {

        List<String> sList = customerDao.getCustomerName(name);

        return sList;

    }
}
