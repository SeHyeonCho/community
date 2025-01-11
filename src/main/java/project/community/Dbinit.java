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

            for (int i = 1; i <= 100; i++) {
                User user = createUser("user" + i, i + "", "" + i + i + i + "@" + i + ".com");
                userRepository.save(user);

            }



            for (int i = 1; i <= 100; i++) {
                int rand = (int) (Math.random() * 100) + 1;
                User user = userRepository.findByName("user" + rand);

                Post post = createPost("게시판 제목" + i, i + "번째로 쓰인 글 입니다.", user);
                postRepository.save(post);
            }

            for (int i = 1; i <= 1000; i++) {
                int rand1 = (int) (Math.random() * 100) + 1;
                User user = userRepository.findByName("user" + rand1);

                int rand2 = (int) (Math.random() * 100) + 1;
                Post post = postRepository.findByTitle("게시판 제목" + rand2);

                Comment comment = createComment(i + "번째로 쓰인 댓글 입니다.", user, post);
                commentRepository.save(comment);
            }

        }
    }

}
