package com.songmingyu.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts,Long> {
    //ibatis나 mybatis 등에서 Dao라고 불리는 DB Layer 접근자.
    // JPA에서는 Repository라고 부르며 인터페이스로 생성한다. 단순히 인터페이스를 생성한 후 JpaRepository<Entitiy Class, PK Type>를 상속하면 기본적인 CRUD 메소드 자동생성
    //Entity와 Repository는 같은곳에 위치해야함

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findALlDesc();
}
