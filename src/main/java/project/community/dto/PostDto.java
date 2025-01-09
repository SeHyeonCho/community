package project.community.dto;

import lombok.Data;

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
