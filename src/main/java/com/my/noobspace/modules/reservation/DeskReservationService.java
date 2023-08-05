package com.my.noobspace.modules.reservation;

import com.nimbusds.oauth2.sdk.util.singleuse.AlreadyUsedException;

import javax.management.InstanceAlreadyExistsException;
import java.time.LocalDateTime;

public interface DeskReservationService {
    // 좌석 예약
    DeskReservation reservation(String email, Long deskId, LocalDateTime reservationAt) throws InstanceAlreadyExistsException;

    void cancel(String email, Long deskId);

    void checkin(String email, Long reservationId) throws AlreadyUsedException;
}