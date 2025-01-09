package project.community.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    public static Comment createComment(String content, User user, Post post) {
        Comment comment = new Comment();
        comment.content = content;
        comment.user = user;
        comment.post = post;
        return comment;
    }

    public void assignPost(Post post) {
        this.post = post;
    }

    public void assignUser(User user) {
        this.user = user;
        user.getCommentList().add(this);
    }
}
