package com.my.noobspace.modules.reservation;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.account.AccountQueryRepository;
import com.my.noobspace.modules.desk.Desk;
import com.my.noobspace.modules.desk.DeskQueryRepository;
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
    private final DeskQueryRepository deskQueryRepository;
    private final AccountQueryRepository accountQueryRepository;
    @Override
    public DeskReservation reservation(DeskReservationReqDto dto) {

        if (deskReservationQueryRepository.exist(dto.getAccountEmail())) {
            return null;
        }

        Desk findDesk = deskQueryRepository.findById(dto.getDeskId());
        Account findAccount = accountQueryRepository.findByEmail(dto.getAccountEmail());

        if (findDesk == null || findAccount == null) {
            return null;
        }

        DeskReservation deskReservation = DeskReservation.builder()
                .desk(findDesk)
                .account(findAccount)
                .reservationAt(LocalDateTime.now())
                .build();
        return deskReservationRepository.save(deskReservation);
    }
}

