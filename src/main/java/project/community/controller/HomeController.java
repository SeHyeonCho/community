package project.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import project.community.domain.User;
import project.community.dto.SessionConst;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(
            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User user, Model model) {
        if (user != null) {
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("isLogin", false);
        }
        return "home";

    }
}
