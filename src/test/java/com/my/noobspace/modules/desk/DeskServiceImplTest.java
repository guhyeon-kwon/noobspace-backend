package com.my.noobspace.modules.desk;

import com.my.noobspace.modules.desk.dto.req.DeskReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeskServiceImplTest {
    @Autowired
    DeskServiceImpl deskServiceImpl;

    @Test
    public void insert() {
        // given
        DeskReqDto deskReqDto = DeskReqDto.builder()
                .name("test")
                .floor(1)
                .description("test")
                .fixAccountId(1L)
                .fix(1)
                .zoneId(1L)
                .build();
        // when
        deskServiceImpl.insert(deskReqDto);

        // then
    }
}