package project.community.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.community.domain.User;
import project.community.dto.user.UserLoginDto;
import project.community.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public Long save(User user) {
        userRepository.save(user);
        return user.getId();
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    public User loginValidation(UserLoginDto userLoginDto) {
        Long id = userLoginDto.getId();
        String password = userLoginDto.getPassword();

        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

}


