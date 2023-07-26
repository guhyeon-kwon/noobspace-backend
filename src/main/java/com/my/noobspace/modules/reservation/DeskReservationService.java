package com.my.noobspace.modules.reservation;

import com.my.noobspace.modules.reservation.dto.req.DeskReservationReqDto;

public interface DeskReservationService {
    Long insert(DeskReservationReqDto dto);
}