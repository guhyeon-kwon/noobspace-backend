package com.my.noobspace.modules.reservation.dto.req;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.desk.Desk;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DeskReservationReqDto {
    private Long deskId;
    private String accountEmail;
}
