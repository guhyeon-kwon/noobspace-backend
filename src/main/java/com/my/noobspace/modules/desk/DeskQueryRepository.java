package com.my.noobspace.modules.desk;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.desk.dto.req.DeskReqDto;
import com.my.noobspace.modules.reservation.dto.req.DeskReservationReqDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.my.noobspace.modules.account.QAccount.account;
import static com.my.noobspace.modules.desk.QDesk.desk;
import static com.my.noobspace.modules.reservation.QDeskReservation.deskReservation;

@RequiredArgsConstructor
@Repository
@Transactional
public class DeskQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Desk findById(Long id) {
        return jpaQueryFactory.selectFrom(desk)
                .where(desk.id.eq(id))
                .fetchOne();
    }
}
