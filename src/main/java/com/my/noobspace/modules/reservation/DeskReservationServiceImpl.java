package com.my.noobspace.modules.reservation;

import com.my.noobspace.modules.desk.DeskRepository;
import com.my.noobspace.modules.reservation.dto.DeskReservationDto;
import com.my.noobspace.modules.reservation.dto.req.DeskReservationReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DeskReservationServiceImpl implements DeskReservationService{
    private final DeskReservationRepository deskReservationRepository;
    private final DeskReservationQueryRepository deskReservationQueryRepository;
    @Override
    public DeskReservation insert(DeskReservationReqDto dto) {

//        deskReservationQueryRepository.exist(dto.getAccount().getEmail());
//
//
//        DeskReservation deskReservation = DeskReservation.builder()
//                .desk(dto.getDesk())
//                .account(dto.getAccount())
//                .reservationAt(LocalDateTime.now())
//                .checkIn(dto.getCheckIn())
//                .build();
//        return deskReservationRepository.save(deskReservation);
        return null;
    }
}

