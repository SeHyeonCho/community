package project.community.dto.post;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostCreateDto {
    @NotEmpty
    private String title;
    private String content;
    @NotNull
    private Long writerId;
}
