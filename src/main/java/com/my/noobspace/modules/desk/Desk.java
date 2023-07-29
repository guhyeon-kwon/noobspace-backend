package com.my.noobspace.modules.desk;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.account.dto.SignUpFormDto;
import com.my.noobspace.modules.desk.dto.req.DeskReqDto;
import com.my.noobspace.modules.zone.Zone;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Desk {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 좌석명

    private Integer floor; // 층

    private String description; // 좌석설명
    
    private Integer fix; // 고정좌석 여부 0: 고정좌석 아님, 1: 고정좌석

    @OneToOne
    private Account fixAccount; // 고정좌석 사용자

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone; // 존
    
    @Lob @Basic(fetch = FetchType.EAGER)
    private String deskImage; // 좌석 이미지

    Desk(DeskReqDto dto) {
        this.name = dto.getName();
        this.floor = dto.getFloor();
        this.description = dto.getDescription();
        this.fix = dto.getFix();
        this.deskImage = dto.getDeskImage();
    }

    public static Desk from(DeskReqDto dto){
        return new Desk(dto);
    }
}
