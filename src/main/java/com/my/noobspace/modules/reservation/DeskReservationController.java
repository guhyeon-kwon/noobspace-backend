package com.my.noobspace.modules.reservation;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.account.AccountService;
import com.my.noobspace.modules.desk.DeskService;
import com.my.noobspace.modules.reservation.dto.req.DeskReservationReqDto;
import com.my.noobspace.utils.ErrorObject;
import com.my.noobspace.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DeskReservationController {

    private final DeskReservationService deskReservationService;
    private final AccountService accountService;
    private final DeskService deskService;

    @PostMapping("/reservation")
    public ResponseEntity<ReturnObject> reservation(@AuthenticationPrincipal Account account, DeskReservationReqDto dto) {
        ReturnObject returnObject;
        ErrorObject errorObject;

        DeskReservation insert = deskReservationService.reservation(dto);

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
