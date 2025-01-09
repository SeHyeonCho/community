package project.community.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.community.domain.User;
import project.community.dto.UserCreateDto;
import project.community.dto.UserDto;
import project.community.dto.UserListDto;
import project.community.repository.UserRepository;
import project.community.service.UserApiService;
import project.community.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserApiController {
    private final UserApiService userApiService;
    private final UserService userService;

    @GetMapping
    public UserListDto userList() {
        return userApiService.findAllUserDto();
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userApiService.findDto(userId);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        User newUser = userApiService.createUserFromDto(userCreateDto);
        userService.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("create success");
    }


}
