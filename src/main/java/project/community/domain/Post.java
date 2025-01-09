package project.community.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;
import static java.time.LocalDateTime.*;

@Entity
@Getter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String content;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public static Post createPost(String title, String content, User user) {
        Post post = new Post();
        post.title = title;
        post.content = content;
        post.assignUser(user);
        return post;
    }

    public static Post createPost(String title, String content, User user, Category category) {
        Post post = new Post();
        post.title = title;
        post.content = content;
        post.assignUser(user);
        post.assignCategory(category);
        return post;
    }



    public void assignUser(User user) {
        this.user = user;
        user.getPostList().add(this);
    }

    public void addComment(Comment comment) {
        comment.assignPost(this);
        commentList.add(comment);
    }

    public void assignCategory(Category category) {
        this.category = category;
        category.getPostList().add(this);
    }
}
