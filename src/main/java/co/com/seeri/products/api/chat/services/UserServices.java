package co.com.seeri.products.api.chat.services;

import co.com.seeri.products.api.chat.dto.UserDTO;

import java.util.List;

public interface UserServices {

    UserDTO getUserByUserId(Long id);

    UserDTO addUser(UserDTO user);

    List<UserDTO> getAllUsers();

    UserDTO getUserByUsername(String username);

    UserDTO updateUser(String username, UserDTO user);

    boolean deleteUser(String username);

    boolean isUserCreated(UserDTO userDTO);
}
