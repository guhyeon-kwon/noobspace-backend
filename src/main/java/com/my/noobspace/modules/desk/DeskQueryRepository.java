package com.my.noobspace.modules.desk;

import com.my.noobspace.modules.desk.dto.req.DeskReqDto;
import com.my.noobspace.modules.desk.dto.res.DeskResDto;
import com.my.noobspace.modules.desk.dto.res.QDeskResDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<DeskResDto> findByDeskInfo(DeskReqDto dto) {
        return jpaQueryFactory.select(new QDeskResDto(
                        desk.name,
                        desk.floor,
                        desk.description,
                        desk.fix,
                        desk.fixAccount.id,
                        desk.zone.id,
                        desk.deskImage
                ))
                .from(desk)
                .leftJoin(deskReservation)
                .on(deskReservation.desk.id.eq(desk.id))
                .where(eqZone(dto.getZoneId()), eqFloor(dto.getFloor()), isNotNullReservation(dto.getReservationStatus()))
                .fetch();
    }

    private BooleanExpression eqZone(Long zoneId) {
        if (zoneId == null) {
            return null;
        }
        return desk.zone.id.eq(zoneId);
    }

    private BooleanExpression eqFloor(Integer floor) {
        if (floor == null) {
            return null;
        }
        return desk.floor.eq(floor);
    }

    private BooleanExpression isNotNullReservation(Integer reservationStatus) {
        if (reservationStatus == 0) {
            return null;
        }
        return deskReservation.isNotNull();
    }
}
