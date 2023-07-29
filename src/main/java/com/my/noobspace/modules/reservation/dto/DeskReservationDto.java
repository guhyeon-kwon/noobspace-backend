package com.my.noobspace.modules.reservation.dto;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.desk.Desk;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DeskReservationDto {
    private Desk desk;
    private Account account;
    private LocalDateTime reservationAt;
    private LocalDateTime checkInAt;
    private Integer checkIn;
}
