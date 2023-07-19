package com.my.noobspace.modules.account;

import com.my.noobspace.modules.role.Role;
import com.my.noobspace.modules.team.Team;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email; // 이메일

    private String name; // 이름

    private String password; // 비밀번호

    @ManyToOne
    private Team team; // 팀명

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String profileImage; // 프로필 이미지

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role = new ArrayList<>(); // 권한

    private boolean push = true; // 푸시 여부

}