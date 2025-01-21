package project.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import project.community.domain.User;
import project.community.dto.SessionConst;
import project.community.interceptor.Login;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(
            @Login User user, Model model) {
        model.addAttribute("isLogin", user != null);
        return "home";
    }

    @GetMapping("/exception")
    public void exception() {
        throw new IllegalArgumentException();
    }

}
