package project.community.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.community.domain.User;
import project.community.dto.user.UserCreateDto;
import project.community.dto.user.UserDto;
import project.community.dto.user.UserListDto;
import project.community.repository.api.UserApiRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserApiService {
    private final UserApiRepository userApiRepository;

    public UserListDto findAllUserDto() {
        return new UserListDto(userApiRepository.findAllDto());
    }

    public UserDto findDto(Long id) {
        return userApiRepository.findDto(id);
    }

    public User createUserFromDto(UserCreateDto userCreateDto) {
        return User.createUser(userCreateDto.getName(), userCreateDto.getPassword(), userCreateDto.getEmail());
    }

}
