package lee.joohan.SpringBootSimpleBoard.repository;

import lee.joohan.SpringBootSimpleBoard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
