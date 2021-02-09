package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.tests.Tests;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TestDto {

    private String data;

    @Builder
    public Tests toEntity(){
        return Tests.builder().data(data).build();
    }
}
