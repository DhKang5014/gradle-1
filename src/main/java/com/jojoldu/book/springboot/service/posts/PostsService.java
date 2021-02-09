package com.jojoldu.book.springboot.service.posts;


import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Optional<Posts> posts = postsRepository.findById(id);

        if(!posts.isPresent()){
            new IllegalArgumentException("해당 게시글이 없습니다. id = "+id);
        }
        System.out.println(requestDto.toString());
        posts.get().update(requestDto.getTitle(), requestDto.getContent());
        System.out.println(posts.toString());
        //postsRepository.save(posts.get());
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id){
        Optional<Posts> entity = postsRepository.findById(id);

        if(!entity.isPresent()) {
            new IllegalArgumentException("해당 게시글이 없습니다. id = "+id);
        }

        return new PostsResponseDto(entity.get());
    }


    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());

    }
    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        postsRepository.delete(posts);
    }
}
