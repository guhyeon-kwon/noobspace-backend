package com.my.noobspace.modules.reservation;

import com.my.noobspace.modules.reservation.dto.DeskReservationDto;
import com.my.noobspace.modules.reservation.dto.req.DeskReservationReqDto;

public interface DeskReservationService {
    // 좌석 예약
    DeskReservation reservation(DeskReservationReqDto dto);
}