package project.community.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.community.dto.post.PostCreateDto;
import project.community.dto.post.PostDto;
import project.community.dto.post.PostListDto;
import project.community.domain.Post;
import project.community.domain.User;
import project.community.repository.PostRepository;
import project.community.repository.UserRepository;
import project.community.repository.api.PostApiRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostApiService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostApiRepository postApiRepository;

    @Transactional
    public Long savePost(PostCreateDto postCreateDto) {
        User writer = userRepository.findById(postCreateDto.getWriterId()).get();
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

    @Transactional
    public void delete(Post post) {
        postRepository.delete(post);
    }
}
