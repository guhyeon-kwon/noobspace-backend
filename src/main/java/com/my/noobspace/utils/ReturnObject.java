package com.my.noobspace.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

// api 결과값 return 할때 이 객체에 담아서 return
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReturnObject {
    @Builder.Default
    private boolean success = false; // 성공 여부
    private Object data; // 성공시 결과값
    private Object error; // 에러 객체
}
