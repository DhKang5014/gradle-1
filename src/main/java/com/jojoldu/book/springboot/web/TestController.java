package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.domain.tests.Tests;
import com.jojoldu.book.springboot.service.tests.TestsService;
import com.jojoldu.book.springboot.web.dto.TestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestsService testsService;

    @PostMapping("/test")
    public Long test(@RequestBody TestDto  requestDto){
        return testsService.save(requestDto);
    }

    @GetMapping("/gets")
    public Optional<Tests> get(@RequestParam Long id){
        return testsService.selectID(id);
    }
}
