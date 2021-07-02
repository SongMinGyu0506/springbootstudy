package com.songmingyu.admin.web.dto;

import com.songmingyu.admin.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
/*
* 절대로 Entity 클래스를 Request/Response 클래스로 사용해서는 안된다.
* Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스 해당 클래스를 기준으로 테이블이 생성되고 스키마가 변경된다.
* 화면 변경을 위해서 테이블과 직접 연결된 Entity 클래스를 변경하는 것은 리스크가 너무 크다.
*
* 수 많은 서비스 클래스나 비즈니스 로직들이 Entity Class를 기준으로 동작한다. Entity 클래스가 변경된다면 여러 클래스에 영향을 끼치지만
* Request와 Response용 Dto는 View를 위한 클래스라 정말 자주 변경이 되므로 역할 분리를 철저하게 해야한다.
* */
