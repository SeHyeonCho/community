package project.community.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.community.domain.Post;
import project.community.dto.CategoryName;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.user u where u.name = :name")
    List<Post> findAllByWriter(@Param("name") String writer);

    @Override
    @EntityGraph(attributePaths = "user")
    Page<Post> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "user")
    Page<Post> findAllByCategoryName(CategoryName name, Pageable pageable);
}
