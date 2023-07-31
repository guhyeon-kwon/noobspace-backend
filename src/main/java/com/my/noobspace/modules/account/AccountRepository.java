package com.my.noobspace.modules.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
    Optional<Account> findAccountByEmail(String email);
}