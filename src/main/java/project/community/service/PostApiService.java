package project.community.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.community.api.PostCreateDto;
import project.community.api.PostDto;
import project.community.api.PostListDto;
import project.community.domain.Post;
import project.community.domain.User;
import project.community.repository.CommentRepository;
import project.community.repository.PostRepository;
import project.community.repository.UserRepository;
import project.community.repository.api.PostApiRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostApiService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostApiRepository postApiRepository;

    public Long savePost(PostCreateDto postCreateDto) {
        User writer = userRepository.findById(postCreateDto.getWriterId());
        Post newPost = Post.createPost(postCreateDto.getTitle(), postCreateDto.getContent(), writer);
        postRepository.save(newPost);
        return newPost.getId();
    }

    public PostListDto findAll() {
        return new PostListDto(postApiRepository.findAll());
    }

    public PostDto findById(Long id) {
        return postApiRepository.findById(id);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }
}
