package com.my.noobspace.modules.team;

import com.my.noobspace.modules.team.dto.req.TeamReqDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.my.noobspace.modules.team.QTeam.team;

@RequiredArgsConstructor
@Repository
@Transactional
public class TeamQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Long insert(TeamReqDto dto) {
        return jpaQueryFactory
                .insert(team)
                .columns(team.name,
                        team.description)
                .values(dto.getName(),
                        dto.getDescription())
                .execute();
    }
}
