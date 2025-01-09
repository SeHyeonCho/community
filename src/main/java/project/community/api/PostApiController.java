package project.community.api;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import project.community.domain.Post;
import project.community.repository.api.PostApiRepository;
import project.community.service.PostApiService;
import project.community.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
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
