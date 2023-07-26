package com.my.noobspace.modules.reservation;

import com.my.noobspace.modules.reservation.dto.req.DeskReservationReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeskReservationServiceImpl implements DeskReservationService{
    private final DeskReservationQueryRepository deskReservationQueryRepository;
    @Override
    public Long insert(DeskReservationReqDto dto) {
        return deskReservationQueryRepository.insert(dto);
    }
}

