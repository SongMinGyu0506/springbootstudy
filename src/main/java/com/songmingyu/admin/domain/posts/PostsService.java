package com.songmingyu.admin.domain.posts;

import com.songmingyu.admin.web.dto.PostsListResponseDto;
import com.songmingyu.admin.web.dto.PostSaveRequestDto;
import com.songmingyu.admin.web.dto.PostsListResponseDto;
import com.songmingyu.admin.web.dto.PostsResponseDto;
import com.songmingyu.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
//import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostRepository postRepository;

    @Transactional //트랜잭션 방식 사용
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postRepository.findALlDesc().stream().map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public void delete (Long id) {
        Posts posts = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postRepository.delete(posts);
    }

}
