package com.my.noobspace.modules.desk;

import com.my.noobspace.modules.account.dto.SignUpFormDto;
import com.my.noobspace.modules.desk.dto.req.DeskReqDto;

public interface DeskService {
    void insert(DeskReqDto dto);
}
