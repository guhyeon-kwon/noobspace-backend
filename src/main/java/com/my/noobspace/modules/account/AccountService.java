package com.my.noobspace.modules.account;

import com.my.noobspace.modules.account.dto.SignUpFormDto;

import java.util.Optional;

public interface AccountService {
    void insert(SignUpFormDto dto);
    void addTeam(String email, Long teamId);
    Optional<Account> findAccountByEmail(String email);
}
