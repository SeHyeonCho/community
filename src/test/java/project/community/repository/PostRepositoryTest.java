package project.community.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import project.community.domain.Post;
import project.community.service.PostService;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Slf4j
class PostRepositoryTest {
    @Autowired
    PostService postService;
    @Autowired
    UserRepository userRepository;



    @Test
    void 정상_저장() throws Exception {
        //given
        Long userId = 1L;
        String title = "테스트용 제목";
        String content = "다른 누구에게도 의지하지 말고 오직 그대 혼자의 힘으로 하라.";

        //when
        Long saveId = postService.savePost(userId, title, content);
        Post savePost = postService.findById(saveId);

        //then
        assertThat(savePost.getId()).isEqualTo(saveId);
        log.info("savePost.Id = {}", savePost.getId());
        assertThat(savePost.getUser().getName()).isEqualTo(userRepository.findById(userId).getName());
        log.info("savePost.user.name = {}", savePost.getUser().getName());
        assertThat(savePost.getTitle()).isEqualTo(title);
        log.info("savePost.title = {}", savePost.getTitle());
        assertThat(savePost.getContent()).isEqualTo(content);
        log.info("savePost.content = {}", savePost.getContent());
    }
}