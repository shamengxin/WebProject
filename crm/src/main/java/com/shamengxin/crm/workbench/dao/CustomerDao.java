package com.shamengxin.crm.workbench.dao;

import com.shamengxin.crm.workbench.domain.Customer;

public interface CustomerDao {

    Customer getCustomerByName(String company);

    int save(Customer cus);
}
