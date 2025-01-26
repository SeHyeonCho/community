package project.community.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.community.domain.User;
import project.community.dto.SessionConst;
import project.community.dto.user.UserCreateDto;
import project.community.dto.user.UserLoginDto;
import project.community.service.UserService;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String userList(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "users/userList";
    }

    @GetMapping("/login")
    public String userLoginForm() {
        return "users/userLoginForm";
    }

//    @PostMapping("/login")
    public String userLogin(
            @Valid @ModelAttribute("user") UserLoginDto loginDto,
            BindingResult bindingResult,
            @RequestParam(defaultValue = "/") String redirectURI,
            HttpSession session) {

        log.info("로그인 요청");
        if (bindingResult.hasErrors()) {
            return "users/userLoginForm";
        }

        User user = userService.loginValidation(loginDto);
        if (user == null) {
            bindingResult.reject("login", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "users/userLoginForm";
        }
        log.info("로그인 성공 id={}, password={}", loginDto.getUsername(), loginDto.getPassword());

        session.setAttribute(SessionConst.LOGIN_USER, user);
        return "redirect:" + redirectURI;
//        return "redirect:/users";
    }
//    @PostMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }


    @GetMapping("/create")
    public String userCreateForm(@ModelAttribute("user") UserCreateDto userCreateDto) {
        return "users/userCreateForm";
    }

    @PostMapping("/create")
    public String userCreate(@ModelAttribute("user") @Valid UserCreateDto userCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("user has Error : {}", bindingResult.getFieldError());
            return "users/userCreateForm";
        }

        if (userService.findByName(userCreateDto.getName()) != null) {
            log.info("동일한 닉네임이 존재합니다.");
            bindingResult.rejectValue("name", "noUniqueName", "이미 존재하는 닉네임 입니다.");
            return "users/userCreateForm";
        }

        User user = User.createUser(userCreateDto.getName(), userCreateDto.getPassword(), userCreateDto.getEmail());
        userService.save(user);
        return "redirect:/";
    }

    @PostMapping("/{userId}/cancel")
    public String userDelete(@PathVariable Long userId) {
        userService.delete(userId);
        return "redirect:/users";
    }

}
