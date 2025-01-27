package project.community.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.community.domain.Category;
import project.community.domain.Post;
import project.community.domain.User;
import project.community.dto.CategoryName;
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
        User writer = userRepository.findById(userId).get();
        Post newPost = Post.createPost(title, content, writer);
        postRepository.save(newPost);
        return newPost.getId();
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByWriter(String writer) {
        return postRepository.findAllByWriter(writer);
    }

    @Transactional(readOnly = true)
    public Post findById(Long id) {
        return postRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Post> findPageAll(int page, int offset) {
        return postRepository.findAll(PageRequest.of(page, offset));
    }

    @Transactional(readOnly = true)
    public Page<Post> findPageAllByCategoryName(CategoryName name, int page, int offset) {
        return postRepository.findAllByCategoryName(name, PageRequest.of(page, offset));
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllPageByCategory(Category category, int page, int offset) {
        return postRepository.findAll(PageRequest.of(page, offset));
    }

    public Long deletePost(Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }
}
