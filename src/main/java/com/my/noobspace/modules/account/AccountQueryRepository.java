package com.my.noobspace.modules.account;

import com.my.noobspace.modules.team.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.my.noobspace.modules.account.QAccount.account;
import static com.my.noobspace.modules.team.QTeam.team;

@RequiredArgsConstructor
@Repository
@Transactional
public class AccountQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public void update(String email, Long teamId) {
        Account findAccount = jpaQueryFactory.selectFrom(account)
                .where(account.email.eq(email))
                .fetchOne();

        if (findAccount == null) {
            return;
        }

        Team findTeam = jpaQueryFactory.selectFrom(team)
                .where(team.id.eq(teamId))
                .fetchOne();

        if (findTeam == null) {
            return;
        }

        findAccount.setTeam(findTeam);
    }

    public Boolean exist(String email) {
        Integer fetchOne = jpaQueryFactory.selectOne()
                .from(account)
                .where(account.email.eq(email))
                .fetchFirst();

        return fetchOne != null;
    }

    public Account findByEmail(String email) {
        return jpaQueryFactory.selectFrom(account)
                .where(account.email.eq(email))
                .fetchOne();
    }
}
