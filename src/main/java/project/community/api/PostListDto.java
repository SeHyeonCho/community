package project.community.api;

import lombok.Data;

import java.util.List;

@Data
public class PostListDto {

    private List<PostDto> postList;

    public PostListDto(List<PostDto> postList) {
        this.postList = postList;
    }
}
