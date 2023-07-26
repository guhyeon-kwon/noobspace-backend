package com.my.noobspace;

import com.my.noobspace.modules.account.AccountService;
import com.my.noobspace.modules.account.dto.SignUpFormDto;
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


    public static void main(String[] args) {
        SpringApplication.run(NoobspaceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(){
        return args -> {
            addUser();
            addTeam();
            addUserinTeam();
        };
    }

    public void addUser(){
        SignUpFormDto form = SignUpFormDto.builder()
                .email("test@gmail.com")
                .password("qwer1234!")
                .confirmPassword("qwer1234!")
                .name("홍길동")
                .phone("01012345678")
                .build();
        accountService.insert(form);
    }

    public void addTeam(){
        TeamReqDto from = TeamReqDto.builder()
                .name("개발팀")
                .description("개발1팀")
                .build();
        teamService.insert(from);
    }

    public void addUserinTeam(){
        accountService.addTeam("test@gmail.com",1L);
    }

}
