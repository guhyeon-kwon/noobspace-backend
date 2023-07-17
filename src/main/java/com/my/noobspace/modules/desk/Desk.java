package com.my.noobspace.modules.desk;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Desk {
    @Id @GeneratedValue
    private Long id;

    private String title; // 좌석명

    private String zone; // 좌석구역

    private Integer floor; // 층

    private String description; // 좌석설명

    @Lob @Basic(fetch = FetchType.EAGER)
    private String image; // 좌석 이미지
}
