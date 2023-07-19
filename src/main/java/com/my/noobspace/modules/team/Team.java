package com.my.noobspace.modules.team;

import com.my.noobspace.modules.account.Account;
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
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @OneToMany
    @JoinColumn(name = "account_id")
    private List<Account> account;
}
