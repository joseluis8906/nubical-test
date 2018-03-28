package nubical.user;

import java.util.Optional;

//users service interface
public interface UserService {
    Optional<User> findById(Long Id);
    User findUserByUsername(String username);
    void createUser(User user);
    void modifyUser(Long id, User user);
    void deleteUser(Long id);
}
