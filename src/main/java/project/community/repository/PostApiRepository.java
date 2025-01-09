package project.community.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import project.community.dto.PostDto;
import project.community.domain.Post;

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

    public List<Post> findByWriter(String writer) {
        return query.select(post)
                .from(post)
                .join(user).fetchJoin()
                .where(user.name.eq(writer))
                .fetch();
    }
}
