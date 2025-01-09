package project.community.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.community.dto.CommentListDto;
import project.community.repository.CommentApiRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentApiService {
    private final CommentApiRepository commentApiRepository;

    public CommentListDto findAllCommentDto(Long postId) {
        return new CommentListDto(commentApiRepository.findAllDto(postId));
    }
}
