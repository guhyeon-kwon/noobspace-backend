package com.my.noobspace.modules.desk;

import com.my.noobspace.modules.desk.dto.req.DeskReqDto;
import com.my.noobspace.modules.desk.dto.req.DeskSaveReqDto;
import com.my.noobspace.modules.desk.dto.res.DeskResDto;

import java.util.List;

public interface DeskService {
    void insert(DeskSaveReqDto dto);

    List<DeskResDto> getDeskList(DeskReqDto dto);
}
