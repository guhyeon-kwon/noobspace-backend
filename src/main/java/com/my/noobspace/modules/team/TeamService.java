package com.my.noobspace.modules.team;

import com.my.noobspace.modules.reservation.dto.req.DeskReservationReqDto;
import com.my.noobspace.modules.team.dto.req.TeamReqDto;

public interface TeamService {
    Long insert(TeamReqDto dto);
}