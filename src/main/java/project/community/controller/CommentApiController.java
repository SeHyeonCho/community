package project.community.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.community.dto.CommentListDto;
import project.community.service.CommentApiService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentApiController {

    private final CommentApiService commentApiService;


    @GetMapping
    public CommentListDto getCommentList(@PathVariable Long postId) {
        return commentApiService.findAllCommentDto(postId);
    }
}
