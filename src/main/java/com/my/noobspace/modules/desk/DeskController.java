package com.my.noobspace.modules.desk;

import com.my.noobspace.modules.desk.dto.req.DeskReqDto;
import com.my.noobspace.modules.desk.dto.res.DeskResDto;
import com.my.noobspace.utils.ErrorObject;
import com.my.noobspace.utils.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DeskController {

    private final DeskService deskService;

    @GetMapping("/desk")
    public ResponseEntity<ReturnObject> getDeskList(DeskReqDto dto, Errors errors) {
        ReturnObject returnObject;
        ErrorObject errorObject;

        if (errors.hasErrors()) {
            errorObject = ErrorObject.builder().code(errors.getFieldError().getCode()).message(errors.getFieldError().getDefaultMessage()).build();
            returnObject = ReturnObject.builder().success(false).error(errorObject).build();

            return ResponseEntity.ok().body(returnObject);
        } else {
            List<DeskResDto> deskList = deskService.getDeskList(dto);

            returnObject = ReturnObject.builder().success(true).data(deskList).build();
            return ResponseEntity.ok().body(returnObject);
        }
    }

}
