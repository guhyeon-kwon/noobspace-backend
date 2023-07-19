package com.my.noobspace.modules.desk;

import com.my.noobspace.modules.zone.Zone;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Desk {
    @Id @GeneratedValue
    private Long id;

    private String title; // 좌석명

    private Integer floor; // 층

    private String description; // 좌석설명

    @Lob @Basic(fetch = FetchType.EAGER)
    private String deskImage; // 좌석 이미지
}
