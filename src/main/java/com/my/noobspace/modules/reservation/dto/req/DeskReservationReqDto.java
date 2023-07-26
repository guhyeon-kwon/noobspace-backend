package com.my.noobspace.modules.reservation.dto.req;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DeskReservationReqDto {
    private Long deskId;
    private String accountEmail;
}
