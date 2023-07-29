package com.my.noobspace.modules.role;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.my.noobspace.modules.role.QRole.role;

@RequiredArgsConstructor
@Repository
@Transactional
public class RoleQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Role findByName(String name) {
        return jpaQueryFactory.selectFrom(role)
                .where(role.name.eq(name))
                .fetchOne();
    }
}
