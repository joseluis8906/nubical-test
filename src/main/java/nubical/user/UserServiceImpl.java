package nubical.user;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

//implementation of users service interface
@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> findById (Long id){
        return this.userRepository.findById(id);
    }

    public User findUserByUsername (String username){
        return this.userRepository.findByUsername(username);
    }

    public void createUser(User user){
        this.userRepository.save(user);
    }

    public void modifyUser(Long id, User user){
        User _user = this.userRepository.findByUsername(user.getUsername());
        if(null != _user) {
            user.setId(_user.getId());
            this.userRepository.save(user);
        }
    }

    public void deleteUser(Long id){
        this.userRepository.deleteById(id);
    }
}
