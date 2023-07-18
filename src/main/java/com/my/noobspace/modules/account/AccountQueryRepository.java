package com.my.noobspace.modules.account;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.my.noobspace.modules.account.QAccount.account;

@RequiredArgsConstructor
@Repository
public class AccountQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Account findByEmail(String email) {
        return jpaQueryFactory
                .selectFrom(account)
                .where(account.email.eq(email))
                .fetchOne();
    }
}
