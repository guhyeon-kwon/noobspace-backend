package com.my.noobspace;

import com.my.noobspace.modules.account.AccountService;
import com.my.noobspace.modules.account.dto.SignUpFormDto;
import com.my.noobspace.modules.desk.DeskService;
import com.my.noobspace.modules.desk.dto.req.DeskReqDto;
import com.my.noobspace.modules.role.Role;
import com.my.noobspace.modules.role.RoleRepository;
import com.my.noobspace.modules.team.TeamService;
import com.my.noobspace.modules.team.dto.req.TeamReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NoobspaceApplication {

    @Autowired
    AccountService accountService;

    @Autowired
    TeamService teamService;

    @Autowired
    DeskService deskService;

    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(NoobspaceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(){
        return args -> {
            addRole();
            addUser();
            addTeam();
            addUserinTeam();
            addDesk();
        };
    }

    public void addRole(){
        Role roleUser = Role.builder()
                .name("ROLE_USER")
                .build();
        Role roleAdmin = Role.builder()
                .name("ROLE_ADMIN")
                .build();

        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
    }

    public void addUser(){
        SignUpFormDto formUser = SignUpFormDto.builder()
                .email("user@gmail.com")
                .password("qwer1234!")
                .confirmPassword("qwer1234!")
                .name("김유저")
                .phone("01012345678")
                .roleName("ROLE_USER")
                .build();
        accountService.insert(formUser);

        SignUpFormDto formAdmin = SignUpFormDto.builder()
                .email("admin@gmail.com")
                .password("qwer1234!")
                .confirmPassword("qwer1234!")
                .name("김관리자")
                .phone("01055556666")
                .roleName("ROLE_ADMIN")
                .build();
        accountService.insert(formAdmin);
    }

    public void addTeam(){
        TeamReqDto from = TeamReqDto.builder()
                .name("개발팀")
                .description("개발1팀")
                .build();
        teamService.insert(from);
    }

    public void addDesk(){
        DeskReqDto from = DeskReqDto.builder()
                .name("A1")
                .floor(1)
                .zoneId(1L)
                .fix(0)
                .description("벽쪽 A1 책상")
                .build();
        DeskReqDto from2 = DeskReqDto.builder()
                .name("A2")
                .floor(1)
                .zoneId(1L)
                .fix(0)
                .description("벽쪽 A2 책상")
                .build();
        deskService.insert(from);
        deskService.insert(from2);
    }

    public void addUserinTeam(){
        accountService.addTeam("test@gmail.com",1L);
    }

}
