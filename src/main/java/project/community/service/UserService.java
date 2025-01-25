package project.community.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional
    public Long save(User user) {
        userRepository.save(User.createUser(user.getName(), passwordEncoder.encode(user.getPassword()), user.getEmail()));
        return user.getId();
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    public User loginValidation(UserLoginDto userLoginDto) {
        Long username = userLoginDto.getUsername();
        Optional<User> user = userRepository.findById(username);

        boolean matches = passwordEncoder.matches(userLoginDto.getPassword(), user.get().getPassword());

        if (matches) {
            return user.get();
        } else {
            return null;
        }
    }

}


