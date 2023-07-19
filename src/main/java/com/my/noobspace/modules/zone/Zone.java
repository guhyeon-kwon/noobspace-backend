package com.my.noobspace.modules.zone;

import com.my.noobspace.modules.desk.Desk;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Zone {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @OneToMany
    @JoinColumn(name = "desk_id")
    private List<Desk> desk;
}
