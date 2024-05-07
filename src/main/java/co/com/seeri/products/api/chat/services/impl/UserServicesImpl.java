package co.com.seeri.products.api.chat.services.impl;


import co.com.seeri.products.api.chat.dto.UserDTO;
import co.com.seeri.products.api.chat.entities.User;
import co.com.seeri.products.api.chat.mappers.UserMapper;
import co.com.seeri.products.api.chat.repositories.UserRepository;
import co.com.seeri.products.api.chat.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServicesImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getUserByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::mapUserToUserDTO).orElse(null);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User userEntity = userMapper.mapUserDTOToUser(userDTO);
        userEntity = userRepository.saveAndFlush(userEntity);
        return userMapper.mapUserToUserDTO(userEntity);
    }
}
