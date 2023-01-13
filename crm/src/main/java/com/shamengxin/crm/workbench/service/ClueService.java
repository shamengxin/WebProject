package com.shamengxin.crm.workbench.service;

import com.shamengxin.crm.workbench.domain.Clue;

public interface ClueService {
    boolean save(Clue clue);

    Clue detail(String id);

    boolean unbund(String id);

    boolean bund(String cid, String[] aids);
}
