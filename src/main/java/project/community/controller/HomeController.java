package project.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.community.domain.Post;
import project.community.dto.CategoryName;
import project.community.service.PostService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String home(Model model) {
        List<Post> freePosts = postService.findPageAllByCategoryName(CategoryName.FREE, 0, 5).getContent();
        model.addAttribute("freePosts", freePosts);
        List<Post> funPosts = postService.findPageAllByCategoryName(CategoryName.FUN, 0, 5).getContent();
        model.addAttribute("funPosts", funPosts);
        return "home";
    }

    @GetMapping("/exception")
    public void exception() {
        throw new IllegalArgumentException();
    }

}
