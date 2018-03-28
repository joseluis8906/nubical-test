package nubical.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//users controller
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserServiceImpl userServiceImpl;

    public UserController (UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username){
        User _user = this.userServiceImpl.findUserByUsername(username);
        if(null != _user){
            return new ResponseEntity<>(_user, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody User user){
        this.userServiceImpl.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> modifyUser(@PathVariable Long id, @RequestBody User user){
        Optional<User> _user = this.userServiceImpl.findById(id);
        if(null != _user){
            this.userServiceImpl.modifyUser(id, user);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        this.userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
