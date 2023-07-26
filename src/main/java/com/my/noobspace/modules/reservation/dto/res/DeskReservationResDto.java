package com.my.noobspace.modules.reservation.dto.res;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeskReservationResDto {
    private Long deskId;
    private String accountEmail;
}
