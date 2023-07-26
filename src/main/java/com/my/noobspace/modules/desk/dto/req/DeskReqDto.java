package com.my.noobspace.modules.desk.dto.req;

import jakarta.persistence.Basic;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeskReqDto {
    private String name;
    private Integer floor;
    private String description;
    private Long fixAccountId;
    private Integer fix;
    private Long zoneId;
    private String deskImage;
}
