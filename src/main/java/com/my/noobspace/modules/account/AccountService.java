package com.my.noobspace.modules.account;

import com.my.noobspace.modules.account.dto.SignUpFormDto;

public interface AccountService {
    void insert(SignUpFormDto dto);
    void addTeam(String email, Long teamId);
}
