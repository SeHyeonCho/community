package project.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.community.domain.Post;
import project.community.domain.User;
import project.community.interceptor.Login;
import project.community.service.PostService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String home(Model model) {
        List<Post> posts = postService.findAllPage(0, 5).getContent();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/exception")
    public void exception() {
        throw new IllegalArgumentException();
    }

}
