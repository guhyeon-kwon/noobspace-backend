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

    @Builder.Default
    private Integer push = 0; // 푸시 여부 0: 푸시안함, 1: 푸시함

    Account(SignUpFormDto dto) {
        this.email = dto.getEmail();
        this.name = dto.getName();
        this.password = dto.getPassword();
        this.profileImage = dto.getProfileImage();
    }

    public static Account from(SignUpFormDto dto){
        return new Account(dto);
    }

}