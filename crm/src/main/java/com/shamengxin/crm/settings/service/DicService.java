package com.shamengxin.crm.settings.service;

import com.shamengxin.crm.settings.domain.DicValue;

import java.util.List;
import java.util.Map;

public interface DicService {
    Map<String, List<DicValue>> getAll();
}
