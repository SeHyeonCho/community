package project.community.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import project.community.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void 중복유저_오류() throws Exception {
        //given
        User user1 = User.createUser("userA");
        userRepository.save(user1);
        //when
        User user2 = User.createUser("userA");
        //then
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user2));
    }

}