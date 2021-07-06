package com.songmingyu.admin.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//User의 CRUD를 책임지는 인터페이스
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email); //기존 첫 구분
}
