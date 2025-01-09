package project.community.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.community.domain.Post;

import java.util.List;

import static project.community.domain.QPost.*;
import static project.community.domain.QUser.*;

public interface PostRepository extends JpaRepository<Post, Long> {
}
