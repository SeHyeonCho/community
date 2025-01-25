package project.community.dto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.community.domain.User;
import project.community.repository.UserRepository;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username ={}",username);
        if (username.isEmpty()) {
            throw new UsernameNotFoundException("user is empty");
        }
        User user = userRepository.findByName(username);
        if (user == null) {
            log.info("user가 NUll 입니다");
            throw new UsernameNotFoundException("user is null");
        }
        log.info("user가 NUll이 아닙니다");
        return new UserDetailImpl(user);
    }

}
