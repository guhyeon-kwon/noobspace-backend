package com.my.noobspace.modules.reservation;

import com.my.noobspace.modules.reservation.dto.req.DeskReservationReqDto;
import com.my.noobspace.utils.ErrorObject;
import com.my.noobspace.utils.ReturnObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class DeskReservationController {

    private final DeskReservationService deskReservationService;

    // 좌석예약
    @PostMapping("/reservation/{id}")
    public ResponseEntity<ReturnObject> reservation(@AuthenticationPrincipal String email, @RequestBody @Valid DeskReservationReqDto dto, @PathVariable Long id) {
        ReturnObject returnObject;
        ErrorObject errorObject;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime reservationAtFormat = LocalDateTime.parse(dto.getReservationAt(), formatter);

        try{
            deskReservationService.reservation(email, id, reservationAtFormat);
            returnObject = ReturnObject.builder().success(true).build();
            return ResponseEntity.ok().body(returnObject);
        } catch (Exception ex){
            errorObject = ErrorObject.builder().code("reservation_exception").message(ex.getMessage()).build();
            returnObject = ReturnObject.builder().success(false).error(errorObject).build();
            return ResponseEntity.ok().body(returnObject);
        }
    }

    // 예약취소
    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<ReturnObject> cancel(@AuthenticationPrincipal String email, @PathVariable Long id){
        ReturnObject returnObject;
        ErrorObject errorObject;

        try{
            deskReservationService.cancel(email, id);
            returnObject = ReturnObject.builder().success(true).build();
            return ResponseEntity.ok().body(returnObject);
        } catch (Exception ex){
            errorObject = ErrorObject.builder().code("reservation_cancel_exception").message(ex.getMessage()).build();
            returnObject = ReturnObject.builder().success(false).error(errorObject).build();
            return ResponseEntity.ok().body(returnObject);
        }
    }

    // 체크인
    @PostMapping("/checkin/{reservationId}")
    public ResponseEntity<ReturnObject> checkin(@AuthenticationPrincipal String email, @PathVariable Long reservationId){
        ReturnObject returnObject;
        ErrorObject errorObject;

        try{
            deskReservationService.checkin(email, reservationId);
            returnObject = ReturnObject.builder().success(true).build();
            return ResponseEntity.ok().body(returnObject);
        } catch (Exception ex){
            errorObject = ErrorObject.builder().code("reservation_cancel_exception").message(ex.getMessage()).build();
            returnObject = ReturnObject.builder().success(false).error(errorObject).build();
            return ResponseEntity.ok().body(returnObject);
        }
    }
}
