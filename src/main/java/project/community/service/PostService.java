package project.community.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.community.domain.Post;
import project.community.domain.User;
import project.community.repository.CommentRepository;
import project.community.repository.PostRepository;
import project.community.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public Long savePost(Long userId, String title, String content) {
        User writer = userRepository.findById(userId);
        Post newPost = Post.createPost(title, content, writer);
        postRepository.save(newPost);
        return newPost.getId();
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByName(String name) {
        return postRepository.findAllByName(name);
    }

    @Transactional(readOnly = true)
    public Post findById(Long id) {
        return postRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
