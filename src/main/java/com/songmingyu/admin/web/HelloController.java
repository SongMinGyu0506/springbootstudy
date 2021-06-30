package com.songmingyu.admin.web;

import com.songmingyu.admin.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 준다.
public class HelloController {
    @GetMapping("/hello") //GET 요청 받는 API를 만들어준다.
    public String hello() {
        return "hello";
    }
    @GetMapping("/hello/dto")
    /*@RequestParam 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
    * 여기에서는 외부에서 name (@RequestParam("name"))이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장하게 된다.*/
    public HelloResponseDto helloResponseDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name,amount);
    }
}
