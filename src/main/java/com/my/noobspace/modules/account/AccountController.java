package com.my.noobspace.modules.account;

import com.my.noobspace.modules.account.dto.SignUpFormDto;
import com.my.noobspace.utils.ErrorObject;
import com.my.noobspace.utils.ReturnObject;
import com.my.noobspace.utils.SignUpFormValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final SignUpFormValidator signUpFormValidator;
    private final PasswordEncoder passwordEncoder;

    private final AccountService accountService;

    @InitBinder("signUpFormDto")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    @PostMapping("/account")
    public ResponseEntity<ReturnObject> saveAccount(@RequestBody @Valid SignUpFormDto signUpFormDto, HttpServletRequest request, Errors errors) {
        ReturnObject returnObject;
        ErrorObject errorObject;

        if (errors.hasErrors()) {
            errorObject = ErrorObject.builder().code(errors.getFieldError().getCode()).message(errors.getFieldError().getDefaultMessage()).build();
            returnObject = ReturnObject.builder().success(false).error(errorObject).build();

            return ResponseEntity.ok().body(returnObject);
        } else {
            signUpFormDto.setPassword(passwordEncoder.encode(signUpFormDto.getPassword()));
            accountService.insert(signUpFormDto);

            returnObject = ReturnObject.builder().success(true).build();
            return ResponseEntity.ok().body(returnObject);
        }
    }
}
