package com.songmingyu.admin.web;

import com.songmingyu.admin.config.auth.LoginUser;
import com.songmingyu.admin.config.auth.dto.SessionUser;
import com.songmingyu.admin.domain.posts.PostsService;
import com.songmingyu.admin.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    //Model --> Server Template Engine에서 사용할 수 있는 객체를 저장할 수 있음
    //여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달.
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts",postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");
        //기존 SessionUser 가져오는 값 개선 이제 어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있음
        if(user != null) {
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }
    /*머스테치 스타터로 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정*/

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
