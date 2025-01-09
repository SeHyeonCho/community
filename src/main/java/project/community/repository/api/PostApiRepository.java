package project.community.repository.api;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;
import project.community.api.PostDto;
import project.community.api.PostListDto;
import project.community.domain.QPost;
import project.community.domain.QUser;

import java.util.List;

import static com.querydsl.core.types.Projections.*;
import static project.community.domain.QPost.*;
import static project.community.domain.QUser.*;

@Repository
public class PostApiRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public PostApiRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public List<PostDto> findAll() {
        return query.select(constructor(PostDto.class
                        , post.title, post.content, user.name, post.createdDate))
                .from(post)
                .join(post.user, user)
                .fetch();
    }

    public PostDto findById(Long id) {
        return query.select(constructor(PostDto.class,
                        post.title, post.content, user.name, post.createdDate))
                .from(post)
                .join(post.user, user)
                .where(post.id.eq(id))
                .fetchOne();
    }
}
