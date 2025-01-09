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


            User user1 = createUser("userA", "0000", "aaa@a.com");
            userRepository.save(user1);
            User user2 = createUser("userB", "1111", "bbb@a.com");
            userRepository.save(user2);
            User user3 = createUser("userC", "2222", "ccc@b.com");
            userRepository.save(user3);

            Post post1 = createPost("개쩌는제목", "본문은 생략한다", user1);
            postRepository.save(post1);
            Post post2 = createPost("어그로성제목", "냥냥냥냥냥냥냥", user2);
            postRepository.save(post2);
            Post post3 = createPost("개쩌는제목", "긴                        본문", user3);
            postRepository.save(post3);

            Comment comment1 = createComment("정말 유익하네요", user2, post1);
            Comment comment2 = createComment("정말 도움됩니다", user3, post1);

            Comment comment3 = createComment("오잉", user1, post2);
            Comment comment4 = createComment("냥냥", user3, post2);

            Comment comment5 = createComment("퍼가요", user1, post3);
            Comment comment6 = createComment("비밀댓글 어쩌구", user2, post3);

            commentRepository.save(comment1);
            commentRepository.save(comment2);
            commentRepository.save(comment3);
            commentRepository.save(comment4);
            commentRepository.save(comment5);
            commentRepository.save(comment6);
        }
    }

}
