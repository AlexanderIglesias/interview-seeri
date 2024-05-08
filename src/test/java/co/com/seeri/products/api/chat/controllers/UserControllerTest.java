package co.com.seeri.products.api.chat.controllers;

import co.com.seeri.products.api.chat.dto.UserDTO;
import co.com.seeri.products.api.chat.services.UserServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServices userServices;

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("username");
        userDTO.setEmail("email");
        ResponseEntity<UserDTO> response = userController.createUser(userDTO);
        verify(userServices).addUser(userDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testCreateUserUserExist() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("username");
        userDTO.setEmail("email");

        when(userServices.isUserCreated(userDTO)).thenReturn(true);
        ResponseEntity<UserDTO> response = userController.createUser(userDTO);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testGetUser() {
        when(userServices.getUserByUserId(1L)).thenReturn(new UserDTO());
        ResponseEntity<UserDTO> response = userController.getUser(1L);
        verify(userServices).getUserByUserId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetUserNull() {
        when(userServices.getUserByUserId(1L)).thenReturn(null);
        ResponseEntity<UserDTO> response = userController.getUser(1L);
        verify(userServices).getUserByUserId(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllUsersEmptyList() {
        List<UserDTO> emptyList = Collections.emptyList();
        when(userServices.getAllUsers()).thenReturn(emptyList);
        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetAllUsersNotEmptyList() {
        List<UserDTO> userList = Arrays.asList(new UserDTO(), new UserDTO());
        when(userServices.getAllUsers()).thenReturn(userList);
        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }


    @Test
    public void testGetUserByUsernameExistingUser() {
        String username = "userTest";
        UserDTO userDTO = new UserDTO();
        when(userServices.getUserByUsername(username)).thenReturn(userDTO);
        ResponseEntity<UserDTO> response = userController.getUserByUsername(username);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    public void testGetUserByUsernameNonexistentUser() {
        String username = "test userTest";
        when(userServices.getUserByUsername(username)).thenReturn(null);
        ResponseEntity<UserDTO> response = userController.getUserByUsername(username);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateUserOk() {
        String username = "userTest";
        UserDTO userDTO = new UserDTO();
        when(userServices.updateUser(username, userDTO)).thenReturn(userDTO);
        ResponseEntity<UserDTO> response = userController.updateUser(username, userDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }
}
