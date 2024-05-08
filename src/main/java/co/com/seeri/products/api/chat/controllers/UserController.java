package co.com.seeri.products.api.chat.controllers;

import co.com.seeri.products.api.chat.dto.UserDTO;
import co.com.seeri.products.api.chat.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {

    private final static Logger logger = Logger.getLogger(UserController.class.getName());

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/getById/{id}")
    @Operation(summary = "get user")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return Optional.ofNullable(userServices.getUserByUserId(id)).map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    @Operation(summary = "create user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

        if (userServices.isUserCreated(userDTO)) {
            logger.info("User already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.addUser(userDTO));
    }

    @GetMapping("/getAllUsers")
    @Operation(summary = "get all users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userServices.getAllUsers();
        if (users.isEmpty()) {
            logger.info("No users found");
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(users);
        }

    }

    @GetMapping("/{username}")
    @Operation(summary = "get user by name")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return Optional.ofNullable(userServices.getUserByUsername(username)).map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/{username}")
    @Operation(summary = "update user data")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String username, @Valid @RequestBody UserDTO userDTO) {
        return Optional.ofNullable(userServices.updateUser(username, userDTO)).map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{username}")
    @Operation(summary = "delete user")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        if (userServices.deleteUser(username)) {
            logger.info("User deleted successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
