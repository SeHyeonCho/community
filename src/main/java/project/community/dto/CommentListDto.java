package project.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentListDto {
    List<CommentDto> comments;

    public CommentListDto(List<CommentDto> comments) {
        this.comments = comments;
    }
}
