package project.community.dto.post;

import lombok.Data;

import java.util.List;

@Data
public class PostListDto {

    private List<PostDto> postList;

    public PostListDto(List<PostDto> postList) {
        this.postList = postList;
    }
}
