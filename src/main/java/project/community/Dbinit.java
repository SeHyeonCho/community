package project.community;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.community.domain.Comment;
import project.community.domain.Post;
import project.community.domain.User;
import project.community.repository.CommentRepository;
import project.community.repository.PostRepository;
import project.community.repository.UserRepository;

import static project.community.domain.Comment.*;
import static project.community.domain.Post.*;
import static project.community.domain.User.*;


/**
 * USER - [userA, userB, userC]
 * POST - []
 */
@Component
@RequiredArgsConstructor
public class Dbinit {

    private final DbInitService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class DbInitService {
        private final CommentRepository commentRepository;
        private final PostRepository postRepository;
        private final UserRepository userRepository;

        public void init() {
            for (int i = 1; i <= 10; i++) {
                User user = createUser("user" + i, i + "", "" + i + i + i + "@" + i + ".com");
                userRepository.save(user);
            }

            for (int i = 1; i <= 10; i++) {
                int rand = (int) (Math.random() * 10) + 1;
                User user = userRepository.findByName("user" + rand);

                Post post = createPost("게시판 제목" + i, i + "번째로 쓰인 글 입니다.", user);
                postRepository.save(post);
            }
            userRepository.save(createUser("1","1234","1@1.com"));

        }
    }

}
