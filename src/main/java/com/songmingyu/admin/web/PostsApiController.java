package com.songmingyu.admin.web;

import com.songmingyu.admin.domain.posts.PostsService;
import com.songmingyu.admin.web.dto.PostSaveRequestDto;
import com.songmingyu.admin.web.dto.PostsResponseDto;
import com.songmingyu.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    //@RequestBody : HTTP 요청 BODY를 자바 객체로 전달 받음
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }
    @PutMapping("/api/v1/posts/{id}")
    //@PathVariable ==> Mapping의 {id} 지정 역할
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id,requestDto);
    }
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
/*Controller와 Service에서 Autowired가 없는 이유?
* Spring에서 Bean을 주입받는 방식
*   Bean? 데이터를 표현하는 것을 목적으로 하는 자바 클래스, 컴포넌트와 비슷한 의미로도 사용된다. javabean 규격서 따라 작성된 자바 클래스를 가리킨다.
* @Autowired setter 생성자
*
* 이 중 가장 권장하는 방식이 생성자로 주입받는 방식 생성자로 Bean 객체를 받도록 하면 @Autowired와 동일한 효과를 볼 수 있음
* 여기서 생성자는 @RequiredArgsConstructor로 해결
* 어노테이션 사용 이유는 의존성 관계를 해결하기 위함
 */
