package com.my.noobspace.modules.reservation;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.account.AccountQueryRepository;
import com.my.noobspace.modules.desk.Desk;
import com.my.noobspace.modules.desk.DeskQueryRepository;
import com.nimbusds.oauth2.sdk.util.singleuse.AlreadyUsedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DeskReservationServiceImpl implements DeskReservationService{
    private final DeskReservationRepository deskReservationRepository;
    private final DeskReservationQueryRepository deskReservationQueryRepository;
    private final DeskQueryRepository deskQueryRepository;
    private final AccountQueryRepository accountQueryRepository;
    @Override
    public DeskReservation reservation(String email, Long deskId, LocalDateTime reservationAt) throws InstanceAlreadyExistsException {

        if (deskReservationQueryRepository.exist(email)) {
            throw new InstanceAlreadyExistsException("이미 예약한 좌석이 있습니다.");
        }

        Desk findDesk = deskQueryRepository.findById(deskId);
        Account findAccount = accountQueryRepository.findByEmail(email);

        if (findDesk == null) {
            throw new NullPointerException("존재하지 않는 좌석입니다.");
        }

        DeskReservation deskReservation = DeskReservation.builder()
                .desk(findDesk)
                .account(findAccount)
                .reservationAt(reservationAt)
                .createdAt(LocalDateTime.now())
                .build();
        return deskReservationRepository.save(deskReservation);
    }

    @Override
    public void cancel(String email, Long deskId) {
        if (!deskReservationQueryRepository.existByEmailAndDeskId(email, deskId)) {
            throw new NullPointerException("존재하지 않는 예약입니다.");
        }

        DeskReservation findDeskReservation = deskReservationQueryRepository.findByEmailAndDeskId(email, deskId);
        deskReservationRepository.delete(findDeskReservation);
    }

    @Override
    public void checkin(String email, Long reservationId) throws AlreadyUsedException {
        if (!deskReservationQueryRepository.existByReservationId(email, reservationId)) {
            throw new NullPointerException("존재하지 않는 예약입니다.");
        }
        deskReservationQueryRepository.checkin(email, reservationId);
    }
}

