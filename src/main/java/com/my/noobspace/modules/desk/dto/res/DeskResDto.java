package com.my.noobspace.modules.desk.dto.res;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

// 좌석 목록 조회용 Response Dto
@Data
@Builder
public class DeskResDto {
    private String name; // 좌석명

    private Integer floor; // 층

    private String description; // 좌석설명

    private Integer fix; // 고정좌석 여부 0: 고정좌석 아님, 1: 고정좌석

    private Long fixAccountId; // 고정좌석 사용자 아이디

    private Long zoneId; // 영역 아이디

    private String deskImage; // 좌석 이미지 경로

    @QueryProjection
    public DeskResDto(String name, Integer floor, String description, Integer fix, Long fixAccountId, Long zoneId, String deskImage) {
        this.name = name;
        this.floor = floor;
        this.description = description;
        this.fix = fix;
        this.fixAccountId = fixAccountId;
        this.zoneId = zoneId;
        this.deskImage = deskImage;
    }
}
