package project.community.repository.api;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import project.community.dto.user.UserDto;

import java.util.List;

import static com.querydsl.core.types.Projections.*;
import static project.community.domain.QUser.*;

@Repository
public class UserApiRepository {
    private final JPAQueryFactory query;

    public UserApiRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<UserDto> findAllDto() {
        return query.select(constructor(UserDto.class, user.id, user.name))
                .from(user)
                .fetch();
    }

    public UserDto findDto(Long id) {
        return query.select(constructor(UserDto.class, user.id, user.name))
                .from(user)
                .where(user.id.eq(id))
                .fetchOne();
    }

}
