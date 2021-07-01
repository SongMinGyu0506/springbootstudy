package com.songmingyu.admin.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter //Getter 자동생성
@NoArgsConstructor //기본 생성자 자동추가
@Entity /*테이블과 링크될 클래스를 나타냄, 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭*/
public class Posts {
    @Id //해당 테이블의 Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙을 나타낸다. GenerationType.IDENTITY 옵션 추가해야 AK 붙음
    private Long id;

    @Column(length = 500, nullable = false) //테이블의 칼럼을 나타내며, 추가 옵션 변경시 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //빌더 패턴 클래스 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
/*
* 어노테이션 순서를 주요 어노테이션을 클래스에 가까이 둔다.
* 주의. setter가 없는 이유는 클래스 인스턴스가 어디서 변해야하는지 코드상으로 명확하게 구분이 불가능함 그래서 Entitiy Class에서는
*   절대 Setter 메소드를 만들지 않는다. 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야함
*
* 빌더 패턴을 사용하게 되면 어느 필드에 어떤 값을 채워야 할지 명확하게 인지 가능함.
* */
