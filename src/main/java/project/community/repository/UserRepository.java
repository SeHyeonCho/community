package project.community.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.community.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
}
