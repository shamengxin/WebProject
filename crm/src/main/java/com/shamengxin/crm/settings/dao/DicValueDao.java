package com.shamengxin.crm.settings.dao;

import com.shamengxin.crm.settings.domain.DicValue;

import java.util.List;

public interface DicValueDao {
    List<DicValue> getListByCode(String code);
}
