package com.songmingyu.admin.web.domain.posts;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.songmingyu.admin.domain.posts.PostRepository;
import com.songmingyu.admin.domain.posts.Posts;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest //사용시 별 문제 없으면 H2 데이터베이스 자동으로 실행
public class PostsRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @After
        //Junit에서 단위 테스트가 끝날 때 마다 수행되는 메소드를 지정
        //보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
        //여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남기 때문에 문제 발생 가능
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    public void TableSave_Load() {
        //given
        String title = "Test title";
        String content = "Test Main";

        //posts 테이블에 insert/update 쿼리 실행 존재 id값의 경우 update 없으면 insert
        postRepository.save(Posts.builder()
                .title(title).content(content)
                .author("ggp04114@naver.com")
                .build());

        //when
        //테이블의 모든 데이터 조회
        List<Posts> postsList = postRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
