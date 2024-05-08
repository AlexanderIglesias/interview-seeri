package co.com.seeri.products.api.chat.services.impl;

import co.com.seeri.products.api.chat.dto.UserDTO;
import co.com.seeri.products.api.chat.entities.User;
import co.com.seeri.products.api.chat.mappers.UserMapper;
import co.com.seeri.products.api.chat.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServicesImplTest {

    @InjectMocks
    private UserServicesImpl userServices;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    User user;
    UserDTO userDTO;

    @Test
    void testGetUserByUserId() {
        user = new User();
        userDTO = new UserDTO();

        user.setId(1L);
        user.setUsername("username");
        user.setEmail("email");

        userDTO.setId(1L);
        userDTO.setUsername("username");
        userDTO.setEmail("email");

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(userMapper.mapUserToUserDTO(user)).thenReturn(userDTO);

        userDTO = userServices.getUserByUserId(1L);
        assertEquals(1L, userDTO.getId());
        assertEquals("username", userDTO.getUsername());
        assertEquals("email", userDTO.getEmail());
    }

    @Test
    void testAddUser() {
        user = new User();
        userDTO = new UserDTO();

        user.setId(1L);
        user.setUsername("username");
        user.setEmail("email");

        userDTO.setId(1L);
        userDTO.setUsername("username");
        userDTO.setEmail("email");

        user = new User();
        userDTO = new UserDTO();

        user.setId(1L);
        user.setUsername("username");
        user.setEmail("email");

        userDTO.setId(1L);
        userDTO.setUsername("username");
        userDTO.setEmail("email");

        userServices.addUser(userDTO);

        assertEquals(1L, userDTO.getId());
        assertEquals("username", userDTO.getUsername());
        assertEquals("email", userDTO.getEmail());

    }

    @Test
    void testAddExistUser() {
        userDTO = new UserDTO();
        userDTO.setUsername("testUser");

        assertNull(userServices.addUser(userDTO));
    }

    @Test
    public void testGetAllUsers_EmptyList() {
        List<User> emptyList = Collections.emptyList();
        when(userRepository.findAll()).thenReturn(emptyList);

        List<UserDTO> response = userServices.getAllUsers();

        // Assert that an empty list is returned
        assertEquals(0, response.size());
    }

    @Test
    public void testGetAllUsersNotEmptyList() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        List<UserDTO> mappedUsers = new ArrayList<>();
        mappedUsers.add(new UserDTO());
        mappedUsers.add(new UserDTO());
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.mapUserToUserDTO(users.get(0))).thenReturn(mappedUsers.get(0));
        when(userMapper.mapUserToUserDTO(users.get(1))).thenReturn(mappedUsers.get(1));

        List<UserDTO> response = userServices.getAllUsers();

        assertEquals(2, response.size());
        assertEquals(mappedUsers.get(0), response.get(0));
    }

    @Test
    public void testGetUserByUsernameExistingUser() {
        String username = "testUser";
        User user = new User();
        UserDTO userDTO = new UserDTO();
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(userMapper.mapUserToUserDTO(user)).thenReturn(userDTO);

        UserDTO response = userServices.getUserByUsername(username);

        assertEquals(userDTO, response);
    }

    @Test
    public void testGetUserByUsernameNonexistentUser() {
        String username = "nonExistentUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        UserDTO response = userServices.getUserByUsername(username);

        assertNull(response);
    }

    @Test
    public void testUpdateUserSuccess() {
        String username = "userTest";
        User existingUser = new User();
        existingUser.setUsername("PablitoPalitos");
        UserDTO updatedUser = new UserDTO();
        updatedUser.setUsername("PepitoPerez");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);
        when(userMapper.mapUserToUserDTO(existingUser)).thenReturn(updatedUser);

        UserDTO response = userServices.updateUser(username, updatedUser);

        assertEquals("PepitoPerez", response.getUsername());
    }


    @Test
    public void testIsUserCreated(){
        userDTO = new UserDTO();
        userDTO.setUsername("username");
        user = new User();
        user.setUsername("username");

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        assertTrue(userServices.isUserCreated(userDTO));
    }



}