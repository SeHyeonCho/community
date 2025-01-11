package project.community.dto.comment;

import lombok.Data;

@Data
public class CommentDto {

    private Long postId;
    private Long writerId;
    private String comment;

    public CommentDto(Long postId, Long writerId, String comment) {
        this.postId = postId;
        this.writerId = writerId;
        this.comment = comment;
    }
}
