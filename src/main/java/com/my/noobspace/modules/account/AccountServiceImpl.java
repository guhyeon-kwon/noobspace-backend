package com.my.noobspace.modules.account;

import com.my.noobspace.modules.account.dto.SignUpFormDto;
import com.my.noobspace.modules.role.Role;
import com.my.noobspace.modules.role.RoleQueryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountQueryRepository accountQueryRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleQueryRepository roleQueryRepository;

    @Override
    public void insert(SignUpFormDto dto) {
        Role findRole = roleQueryRepository.findByName(dto.getRoleName());
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Account account = Account.from(dto);
        account.getRole().add(findRole);
        accountRepository.save(account);
    }

    @Override
    public void addTeam(String email, Long teamId) {
        accountQueryRepository.update(email, teamId);
    }

    @Override
    @Transactional
    public Optional<Account> findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }
}
