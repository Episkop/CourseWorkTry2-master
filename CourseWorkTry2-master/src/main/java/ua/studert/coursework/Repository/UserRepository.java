package ua.studert.coursework.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.studert.coursework.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername (String username);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserEntity findByEmail (String email);
}