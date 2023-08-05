package com.my.noobspace.modules.reservation;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.desk.Desk;
import com.my.noobspace.modules.reservation.dto.req.DeskReservationReqDto;
import com.nimbusds.oauth2.sdk.util.singleuse.AlreadyUsedException;
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

    public Boolean existByEmailAndDeskId(String email, Long deskId) {
        Integer fetchOne = jpaQueryFactory.selectOne()
                .from(deskReservation)
                .where(deskReservation.account.email.eq(email))
                .where(deskReservation.desk.id.eq(deskId))
                .fetchFirst();

        return fetchOne != null;
    }

    public Boolean existByReservationId(String email, Long reservationId) {
        Integer fetchOne = jpaQueryFactory.selectOne()
                .from(deskReservation)
                .where(deskReservation.account.email.eq(email))
                .where(deskReservation.id.eq(reservationId))
                .fetchFirst();

        return fetchOne != null;
    }

    public DeskReservation findByEmailAndDeskId(String email, Long deskId) {
        return jpaQueryFactory.selectFrom(deskReservation)
                .where(deskReservation.account.email.eq(email))
                .where(deskReservation.desk.id.eq(deskId))
                .fetchOne();
    }

    public void checkin(String email, Long reservationId) throws AlreadyUsedException {
        DeskReservation findDeskReservation = jpaQueryFactory.selectFrom(deskReservation)
                .where(deskReservation.account.email.eq(email))
                .where(deskReservation.id.eq(reservationId))
                .fetchOne();

        if (findDeskReservation.getCheckIn() == 1) {
            throw new AlreadyUsedException("이미 체크인이 완료된 예약입니다.");
        }

        findDeskReservation.setCheckInAt(LocalDateTime.now());
        findDeskReservation.setCheckIn(1);
    }
}
