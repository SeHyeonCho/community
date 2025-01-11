package project.community.repository.api;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import project.community.dto.comment.CommentDto;

import java.util.List;

import static com.querydsl.core.types.Projections.*;
import static project.community.domain.QComment.*;
import static project.community.domain.QPost.*;
import static project.community.domain.QUser.*;

@Repository
public class CommentApiRepository {

    private final JPAQueryFactory query;

    public CommentApiRepository(EntityManager em) {
        query = new JPAQueryFactory(em);
    }


    public List<CommentDto> findAllDto(Long postId) {
        return query.select(constructor(CommentDto.class, comment.post.id, comment.user.id, comment.content))
                .from(comment)
                .join(comment.user, user)
                .join(comment.post, post)
                .where(comment.post.id.eq(postId))
                .fetch();
    }

}
