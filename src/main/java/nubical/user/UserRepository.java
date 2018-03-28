package nubical.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//users repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findById(Long id);
}
