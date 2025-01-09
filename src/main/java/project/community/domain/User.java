package project.community.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.*;

@Entity
@Getter
@Table(name = "USERS")
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String password;
    private String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    public static User createUser(String name, String password, String email) {
        User user = new User();
        user.name = name;
        user.password = password;
        user.email = email;
        return user;
    }

    public void addComment(Comment comment) {
        commentList.add(comment);
        comment.assignUser(this);
    }
}
