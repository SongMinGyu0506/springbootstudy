package com.songmingyu.admin.web;

import com.songmingyu.admin.domain.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;
}
