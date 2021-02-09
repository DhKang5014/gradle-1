package com.jojoldu.book.springboot.service.tests;

import com.jojoldu.book.springboot.domain.tests.TestRepository;
import com.jojoldu.book.springboot.domain.tests.Tests;
import com.jojoldu.book.springboot.web.dto.TestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TestsService {

    private final TestRepository testRepository;

    public Long save(TestDto dto){
        return testRepository.save(dto.toEntity()).getId();
    }

    public Optional<Tests> selectID(Long id){
        return testRepository.findById(id);
    }
}
