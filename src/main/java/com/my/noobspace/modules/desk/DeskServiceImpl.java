package com.my.noobspace.modules.desk;

import com.my.noobspace.modules.desk.dto.req.DeskReqDto;
import com.my.noobspace.modules.desk.dto.req.DeskSaveReqDto;
import com.my.noobspace.modules.desk.dto.res.DeskResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeskServiceImpl implements DeskService {

    private final DeskRepository deskRepository;
    private final DeskQueryRepository deskQueryRepository;

    @Override
    public void insert(DeskSaveReqDto dto) {
        Desk fromDesk = Desk.from(dto);
        deskRepository.save(fromDesk);
    }

    @Override
    public List<DeskResDto> getDeskList(DeskReqDto dto) {
        List<DeskResDto> deskResDtos = deskQueryRepository.findByDeskInfo(dto);
        return deskResDtos;
    }
}
