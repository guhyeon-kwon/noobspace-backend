package com.my.noobspace.modules.desk.dto.req;

import lombok.Builder;
import lombok.Data;

// 좌석 등록용 Request Dto
@Data
@Builder
public class DeskSaveReqDto {
    private String name;
    private Integer floor;
    private String description;
    private Long fixAccountId;
    private Integer fix;
    private Long zoneId;
    private String deskImage;
}
