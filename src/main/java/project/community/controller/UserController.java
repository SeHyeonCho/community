package project.community.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.community.domain.User;
import project.community.dto.user.UserCreateDto;
import project.community.repository.UserRepository;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public String userList(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("users", userList);
        return "users/userList";
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
        User user = User.createUser(userCreateDto.getName(), userCreateDto.getPassword(), userCreateDto.getEmail());
        userRepository.save(user);
        return "redirect:/users";
    }
}
