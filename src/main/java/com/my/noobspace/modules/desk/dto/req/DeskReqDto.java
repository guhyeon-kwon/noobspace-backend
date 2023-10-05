package com.my.noobspace.modules.desk.dto.req;

import lombok.Builder;
import lombok.Data;

// 좌석 목록 조회용 Request Dto
@Data
@Builder
public class DeskReqDto {
    private Integer floor; // 층
    private Long zoneId; // 영역 아이디
    private Integer reservationStatus; // 예약 상태 0: 예약 가능, 1: 예약 불가능
}
