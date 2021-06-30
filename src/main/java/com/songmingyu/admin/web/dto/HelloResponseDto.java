package com.songmingyu.admin.web.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

//gradlew wrapper --gradle-version 4.10.2
@Getter
/*
*   Getter 선언된 모든 필드의 get 메소드를 생성
* */
@RequiredArgsConstructor
/*선언된 모든 final 필드가 포함된 생성자를 생성해준다. final 없으면 생성안됨*/
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
