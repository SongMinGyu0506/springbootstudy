package com.songmingyu.admin.domain.user;

import com.songmingyu.admin.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/* 사용자 정보를 담당할 도메인인 User 클래스 */

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    //  JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지 결정
    //  기본적으로 int로 된 숫자가 저장
    //  숫자로 저장되면 데이터베이스로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수가 없음
    //  그래서 문자열로 저장될 수 있도록 선언
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
/*
* User 클래스를 바로 사용할 수 없는 이유
* User 클래스에 직렬화를 구현하지 않았다는 의미의 에러 발생
* 만약 User 클래스에 직렬화 코드를 넣는다면?
* User 클래스가 Entity이기 때문에 Entity는 언제 다른 엔티티와 관계가 형성될지 모른다.
* 그러므로 직렬화 기능을 가진 Dto를 하나 만들어 직렬화를 하는 것이 운영 및 유지보수에 도움이 된다.
*/
