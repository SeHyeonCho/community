package project.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostListDto {

    private List<PostDto> postList;

    public PostListDto(List<PostDto> postList) {
        this.postList = postList;
    }
}
