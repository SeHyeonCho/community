package project.community;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.community.domain.Category;
import project.community.domain.Post;
import project.community.domain.User;
import project.community.dto.CategoryName;
import project.community.repository.CategoryRepository;
import project.community.repository.CommentRepository;
import project.community.repository.PostRepository;
import project.community.repository.UserRepository;
import project.community.service.UserService;

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
        private final UserService userService;
        private final CategoryRepository categoryRepository;

        public void init() {
            Category freeCategory = categoryRepository.save(Category.createCategory(CategoryName.FREE));
            Category funCategory = categoryRepository.save(Category.createCategory(CategoryName.FUN));

            for (int i = 1; i <= 10; i++) {
                User user = createUser("user" + i, i + "", "" + i + i + i + "@" + i + ".com");
                userService.save(user);
            }

            for (int i = 1; i <= 10; i++) {
                int rand = (int) (Math.random() * 10) + 1;
                User user = userRepository.findByName("user" + rand);

                Post post = createPost("게시판 제목" + i+"-자", i + "번째로 쓰인 글 입니다.", user, freeCategory);
                postRepository.save(post);
            }

            for (int i = 1; i <= 10; i++) {
                int rand = (int) (Math.random() * 10) + 1;
                User user = userRepository.findByName("user" + rand);

                Post post = createPost("게시판 제목" + i+"-유", i + "번째로 쓰인 글 입니다.", user, funCategory);
                postRepository.save(post);
            }

            userService.save(createUser("1","1234","1@1.com"));

        }
    }

}
