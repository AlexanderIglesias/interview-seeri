package co.com.seeri.products.api.chat.controllers;

import co.com.seeri.products.api.chat.dto.UserDTO;
import co.com.seeri.products.api.chat.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/{id}")
    @Operation(summary = "get users")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return Optional.ofNullable(userServices.getUserByUserId(id)).map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    @Operation(summary = "create users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.addUser(user));
    }

}
