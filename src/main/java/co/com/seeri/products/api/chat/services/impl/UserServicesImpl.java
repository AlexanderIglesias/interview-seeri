package co.com.seeri.products.api.chat.services.impl;


import co.com.seeri.products.api.chat.dto.UserDTO;
import co.com.seeri.products.api.chat.entities.User;
import co.com.seeri.products.api.chat.mappers.UserMapper;
import co.com.seeri.products.api.chat.repositories.UserRepository;
import co.com.seeri.products.api.chat.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        User userEntity = userRepository.saveAndFlush(userMapper.mapUserDTOToUser(userDTO));
        return userMapper.mapUserToUserDTO(userEntity);
    }


    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userDTOS.add(userMapper.mapUserToUserDTO(user));
        }
        return userDTOS;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(userMapper::mapUserToUserDTO).orElse(null);
    }


    @Override
    public UserDTO updateUser(String username, UserDTO updatedUser) {
        Optional<User> existingUserOptional = userRepository.findByUsername(username);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            return userMapper.mapUserToUserDTO(userRepository.save(existingUser));
        }
        return null;
    }

    @Override
    public boolean deleteUser(String username) {
        Optional<User> toDelete = userRepository.findByUsername(username);
        if (toDelete.isPresent()) {
            userRepository.delete(toDelete.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean isUserCreated(UserDTO userDTO) {
        Optional<User> createdUser = userRepository.findByUsername(userDTO.getUsername());
        return createdUser.isPresent();
    }
}
