package com.shamengxin.crm.workbench.service;

import com.shamengxin.crm.workbench.domain.Tran;

public interface TranService {
    boolean save(Tran t, String customerName);
}
