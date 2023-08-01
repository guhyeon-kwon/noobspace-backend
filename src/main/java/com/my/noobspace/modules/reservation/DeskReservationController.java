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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.InstanceAlreadyExistsException;

@Controller
@RequiredArgsConstructor
public class DeskReservationController {

    private final DeskReservationService deskReservationService;

    @PostMapping("/reservation/{id}")
    public ResponseEntity<ReturnObject> reservation(@AuthenticationPrincipal String email, @PathVariable Long id) {
        ReturnObject returnObject;
        ErrorObject errorObject;

        try{
            deskReservationService.reservation(email, id);
            returnObject = ReturnObject.builder().success(true).build();
            return ResponseEntity.ok().body(returnObject);
        } catch (Exception ex){
            errorObject = ErrorObject.builder().code("reservation_exception").message(ex.getMessage()).build();
            returnObject = ReturnObject.builder().success(false).error(errorObject).build();
            return ResponseEntity.ok().body(returnObject);
        }
    }
}
