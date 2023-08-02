package com.my.noobspace.modules.reservation;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.reservation.dto.DeskReservationDto;
import com.my.noobspace.modules.reservation.dto.req.DeskReservationReqDto;

import javax.management.InstanceAlreadyExistsException;

public interface DeskReservationService {
    // 좌석 예약
    DeskReservation reservation(String email, Long deskId) throws InstanceAlreadyExistsException;

    void cancel(String email, Long deskId);
}