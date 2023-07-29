package com.my.noobspace.utils;

import com.my.noobspace.modules.account.AccountQueryRepository;
import com.my.noobspace.modules.account.dto.SignUpFormDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
@Slf4j
public class SignUpFormValidator implements Validator {

    private final AccountQueryRepository accountQueryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpFormDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpFormDto signUpDto = (SignUpFormDto) target;
        if(accountQueryRepository.exist(signUpDto.getEmail())){
            errors.rejectValue("email", "invalid.email", new Object[]{signUpDto.getEmail()}, "이미 가입된 이메일 입니다.");
        }
        if (!signUpDto.getPassword().equals(signUpDto.getConfirmPassword())){
            errors.rejectValue("password not matched", "failed.password", new Object[]{signUpDto.getEmail()}, "비밀번화와 비밀번호 확인이 일치하지 않습니다.");
        }
    }
}
