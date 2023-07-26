package com.my.noobspace.modules.team;

import com.my.noobspace.modules.team.dto.req.TeamReqDto;
import com.my.noobspace.utils.ErrorObject;
import com.my.noobspace.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/team")
    public ResponseEntity<ReturnObject> reservation(TeamReqDto dto) {
        ReturnObject returnObject;
        ErrorObject errorObject;

        Long insert = teamService.insert(dto);

        if (insert != null) {
            returnObject = ReturnObject.builder().success(true).build();
            return ResponseEntity.ok().body(returnObject);
        } else{
            errorObject = ErrorObject.builder().code("failed_reservation").message("좌석 예약에 실패했습니다.").build();
            returnObject = ReturnObject.builder().success(false).error(errorObject).build();
            return ResponseEntity.ok().body(returnObject);
        }
    }
}
