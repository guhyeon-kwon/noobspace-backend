package com.my.noobspace.modules.desk;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.desk.dto.req.DeskReqDto;
import com.my.noobspace.modules.reservation.dto.req.DeskReservationReqDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.my.noobspace.modules.account.QAccount.account;
import static com.my.noobspace.modules.desk.QDesk.desk;
import static com.my.noobspace.modules.reservation.QDeskReservation.deskReservation;

@RequiredArgsConstructor
@Repository
public class DeskQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public long insert(DeskReqDto dto) {
        return jpaQueryFactory
                .insert(desk)
                .columns(desk.floor,
                        desk.zone.id,
                        desk.description,
                        desk.fix,
                        desk.fixAccount.id,
                        desk.deskImage)
                .values(dto.getFloor(),
                        dto.getZoneId(),
                        dto.getDescription(),
                        dto.getFix(),
                        dto.getFixAccountId(),
                        dto.getDeskImage())
                .execute();
    }
}
