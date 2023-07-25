package com.my.noobspace.modules.reservation;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.desk.Desk;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeskReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "desk_id")
    private Desk desk;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // 예약 시간
    private LocalDateTime reservationAt;

    // 체크인 시간
    private LocalDateTime checkInAt;

    private Integer checkIn = 0; // 0: 체크인 안함, 1: 체크인 함
}