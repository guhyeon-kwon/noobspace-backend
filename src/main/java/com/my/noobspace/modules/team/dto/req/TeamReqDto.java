package com.my.noobspace.modules.team.dto.req;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamReqDto {
    private String name;
    private String description;
}
