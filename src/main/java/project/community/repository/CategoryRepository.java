package project.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.community.domain.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
