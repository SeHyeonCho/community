package project.community.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.community.domain.Comment;
import project.community.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> findByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

}
