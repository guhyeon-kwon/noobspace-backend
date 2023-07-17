package com.my.noobspace.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

// api 결과값 return 할때 이 객체에 담아서 return
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorObject {
    @Builder.Default
    private String code = "failed_request"; // 에러 코드
    @Builder.Default
    private String message = "알수없는 에러입니다."; // 에러 메세지
    private String field; // 에러 발생한 필드
}
