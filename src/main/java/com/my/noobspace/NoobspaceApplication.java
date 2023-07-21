package com.my.noobspace;

import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.account.AccountService;
import com.my.noobspace.modules.account.dto.SignUpFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NoobspaceApplication {

    @Autowired
    AccountService accountService;


    public static void main(String[] args) {
        SpringApplication.run(NoobspaceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(){
        return args -> {
            SignUpFormDto form = SignUpFormDto.builder()
                    .email("test@gmail.com")
                    .password("qwer1234!")
                    .confirmPassword("qwer1234!")
                    .name("홍길동")
                    .build();
            accountService.insert(form);
        };
    }


}
