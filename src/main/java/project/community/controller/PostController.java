package project.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.community.domain.Comment;
import project.community.domain.Post;
import project.community.service.CommentService;
import project.community.service.PostService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    @GetMapping
    public String allPostList(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/posts/postList";
    }

    @GetMapping("/{postId}")
    public String getPost(@PathVariable("postId") Long postId ,Model model) {
        Post post = postService.findById(postId);
        List<Comment> comments = commentService.findByPost(postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "/posts/postDetail";
    }
}
