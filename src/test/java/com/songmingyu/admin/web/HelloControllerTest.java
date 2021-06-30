package com.songmingyu.admin.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
/*
*   테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
*   여기서는 SpringRunner라는 스프링 실행자를 사용한다.
*   즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
*/
@WebMvcTest(controllers = HelloController.class)
/*
*   여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
*   선언할 경우 @Controller @ControllerAdvice 등을 사용할 수 있다.
*   단, @Service, @Component, @Repository 등은 사용할 수 없다.
*   여기서는 컨트롤러만 사용하기 때문에 선언
*/
public class HelloControllerTest {
    @Autowired //Spring이 관리하는 Bean을 주입받음
    private MockMvc mvc;
    /*
    *   웹 API를 테스트할 때 사용
    *   SpringMVC 테스트의 시작점
    *   이 클래스를 통해 HTTP GET,POST 등에 대한 API 테스트 가능
    */

    @Test
    public void Return_Hello() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello"))
                /*
                    MockMvc를 통해 /hello 주소로 HTTP GET 요청
                    체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언할 수 있음
                */
                .andExpect(status().isOk())
                /*
                * mvc.perform의 결과를 검증
                * HTTP Header의 Status를 검증 (200,404,500...)
                * 여기서는 200인지 아닌지를 체크
                * */
                .andExpect(content().string(hello));
                /*
                * mvc.perform의 결과를 검증
                * 응답 본문의 내용을 검증
                * Controller에서 "hello" 리턴하므로 맞는지 검증
                * */
    }

    @Test
    public void Return_HelloresponseDto() throws  Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                    .param("name",name)
                    .param("amount",String.valueOf(amount))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}
