package com.my.noobspace.modules.reservation;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.desk.Desk;
import com.my.noobspace.modules.reservation.dto.req.DeskReservationReqDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Properties;

import static com.my.noobspace.modules.account.QAccount.account;
import static com.my.noobspace.modules.desk.QDesk.desk;
import static com.my.noobspace.modules.reservation.QDeskReservation.deskReservation;

@RequiredArgsConstructor
@Repository
@Transactional
public class DeskReservationQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Boolean exist(String email) {
        Integer fetchOne = jpaQueryFactory.selectOne()
                .from(deskReservation)
                .where(deskReservation.account.email.eq(email))
                .fetchFirst();

        return fetchOne != null;
    }
}
