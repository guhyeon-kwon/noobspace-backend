package com.my.noobspace.modules.account;

import com.my.noobspace.modules.account.dto.SignUpFormDto;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email; // 이메일

    private String name; // 이름

    private String password; // 비밀번호

    private String phone; // 전화번호

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team; // 팀명

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String profileImage; // 프로필 이미지

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private List<Role> role = new ArrayList<>(); // 권한

    @Builder.Default
    private Integer push = 0; // 푸시 여부 0: 푸시안함, 1: 푸시함

    Account(SignUpFormDto dto) {
        this.email = dto.getEmail();
        this.name = dto.getName();
        this.password = dto.getPassword();
        this.phone = dto.getPhone();
        this.profileImage = dto.getProfileImage();
    }

    public static Account from(SignUpFormDto dto){
        return new Account(dto);
    }

}