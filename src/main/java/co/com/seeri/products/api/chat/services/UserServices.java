package co.com.seeri.products.api.chat.services;

import co.com.seeri.products.api.chat.dto.UserDTO;

public interface UserServices {
    UserDTO getUserByUserId(Long id);

    UserDTO addUser(UserDTO user);
}
