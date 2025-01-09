package project.community.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.community.domain.Post;
import project.community.dto.PostCreateDto;
import project.community.dto.PostDto;
import project.community.dto.PostListDto;
import project.community.service.PostApiService;
import project.community.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostApiController {

    private final PostApiService postApiService;
    private final PostService postService;

    @GetMapping
    public PostListDto postList() {
        return postApiService.findAll();
    }

    @GetMapping("/{postId}")
    public PostDto findPost(@PathVariable Long postId) {
        return postApiService.findById(postId);
    }

    @PostMapping
    public void createPost(@RequestBody @Valid PostCreateDto postCreateDto) {
        postApiService.savePost(postCreateDto);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        Post findPost = postService.findById(postId);
        postApiService.delete(findPost);
    }
}
