package inproject.repository;

import inproject.entity.InstagramAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<InstagramAuthUser, Long> {
}
