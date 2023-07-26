package com.my.noobspace.modules.account;

import com.my.noobspace.modules.account.dto.SignUpFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountQueryRepository accountQueryRepository;
    @Override
    public void insert(SignUpFormDto dto) {
        Account account = Account.from(dto);
        accountRepository.save(account);
    }

    @Override
    public void addTeam(String email, Long teamId) {
        accountQueryRepository.update(email, teamId);
    }
}
