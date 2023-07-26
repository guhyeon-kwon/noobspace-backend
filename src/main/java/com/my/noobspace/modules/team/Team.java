package com.my.noobspace.modules.team;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.account.dto.SignUpFormDto;
import com.my.noobspace.modules.team.dto.req.TeamReqDto;
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
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;


    @OneToMany(mappedBy = "team")
    private List<Account> account;

    Team(TeamReqDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
    }

    public static Team from(TeamReqDto dto){
        return new Team(dto);
    }
}
