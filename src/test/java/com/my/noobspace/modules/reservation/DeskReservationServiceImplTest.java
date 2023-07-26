package com.my.noobspace.modules.reservation;

import com.my.noobspace.modules.account.AccountService;
import com.my.noobspace.modules.account.dto.SignUpFormDto;
import com.my.noobspace.modules.reservation.dto.req.DeskReservationReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeskReservationServiceImplTest {
//    @Autowired
//    DeskReservationServiceImpl deskReservationServiceImpl;
//
//    @Autowired
//    AccountService accountService;
//
//    @Test
//    void insert() {
//        SignUpFormDto form = SignUpFormDto.builder()
//                .email("test@gmail.com")
//                .password("qwer1234!")
//                .confirmPassword("qwer1234!")
//                .name("홍길동")
//                .phone("01012345678")
//                .build();
//        accountService.insert(form);
//
//
//        // given
//        DeskReservationReqDto reservationReqDto = DeskReservationReqDto.builder()
//                .deskId(1L)
//                .accountEmail("test@gmail.com")
//                .build();
//
//        // when
//        deskReservationServiceImpl.insert(reservationReqDto);
//
//        // then
//    }
}