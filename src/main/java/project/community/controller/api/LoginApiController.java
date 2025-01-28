package project.community.controller.api;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.community.domain.User;
import project.community.dto.user.UserLoginDto;
import project.community.dto.user.UserResponse;
import project.community.service.JwtService;
import project.community.service.UserService;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginApiController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginDto userLoginDto) {
        User user = userService.loginValidation(userLoginDto);
        return ResponseEntity.ok(new UserResponse(user, jwtService.createToken(user)));
    }



}
