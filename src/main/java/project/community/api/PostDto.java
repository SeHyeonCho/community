package project.community.api;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import project.community.domain.Post;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private String title;
    private String content;
    private String writer;
    private LocalDateTime lastModified;

    public PostDto(String title, String content, String writer, LocalDateTime lastModified) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.lastModified = lastModified;
    }
}
