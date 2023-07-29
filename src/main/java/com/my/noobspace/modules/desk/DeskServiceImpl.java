package com.my.noobspace.modules.desk;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.account.AccountRepository;
import com.my.noobspace.modules.account.AccountService;
import com.my.noobspace.modules.account.dto.SignUpFormDto;
import com.my.noobspace.modules.desk.dto.req.DeskReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeskServiceImpl implements DeskService {

    private final DeskRepository deskRepository;
    @Override
    public void insert(DeskReqDto dto) {
        Desk fromDesk = Desk.from(dto);
        deskRepository.save(fromDesk);
    }
}
