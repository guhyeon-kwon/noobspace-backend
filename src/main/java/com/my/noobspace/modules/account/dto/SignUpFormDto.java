package com.my.noobspace.modules.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpFormDto {
    @NotBlank
    @Email(message = "올바른 이메일 형식으로 입력해주세요")
    private String email;
    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min=2, max = 20, message = "이름은 최소 2자 이상 20자 이하로 입력해주세요.")
    private String name;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 최소 8자 이상 16자 이하, 하나 이상의 문자 및 숫자, 하나 이상의 특수문자가 포함되어야 합니다")
    private String password;
    @NotBlank(message = "비밀번호확인은 필수 입력 값입니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요.")
    private String confirmPassword;
    private String team;
    private String profileImage;
}
