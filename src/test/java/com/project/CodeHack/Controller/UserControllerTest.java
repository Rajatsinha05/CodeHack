package com.project.CodeHack.Controller;
import com.project.CodeHack.Controller.UserController;
import com.project.CodeHack.DTO.UserDto;
import com.project.CodeHack.Service.UserService;
import com.project.CodeHack.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId("1");
        userDto.setUsername("testUser");
        userDto.setScore(50);

        when(userService.registerUser(userDto)).thenReturn(userDto);

        ResponseEntity<UserDto> responseEntity = userController.createUser(userDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userDto, responseEntity.getBody());
    }

    @Test
    public void testGetAllUsers() {
        UserDto userDto = new UserDto();
        userDto.setUserId("1");
        userDto.setUsername("testUser");
        userDto.setScore(50);

        when(userService.getAllUsers()).thenReturn(Collections.singletonList(userDto));

        ResponseEntity<List<UserDto>> responseEntity = userController.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(userDto, responseEntity.getBody().get(0));
    }

    @Test
    public void testGetUserById() {
        UserDto userDto = new UserDto();
        userDto.setUserId("1");
        userDto.setUsername("testUser");
        userDto.setScore(50);

        when(userService.getUserById("1")).thenReturn(userDto);
        when(userService.getUserById("2")).thenThrow(new UserNotFoundException("User not found"));

        ResponseEntity<Object> responseEntity1 = userController.getUserById("1");

        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());
        assertEquals(userDto, responseEntity1.getBody());

        ResponseEntity<Object> responseEntity2 = userController.getUserById("2");

        assertEquals(HttpStatus.NOT_FOUND, responseEntity2.getStatusCode());
//        assertEquals("User not found", responseEntity2.getBody());
    }

    @Test
    public void testUpdateUserScore() {
        UserDto userDto = new UserDto();
        userDto.setUserId("1");
        userDto.setUsername("testUser");
        userDto.setScore(50);

        when(userService.getUserById("1")).thenReturn(userDto);
        when(userService.getUserById("2")).thenThrow(new UserNotFoundException("User not found"));

        ResponseEntity<?> responseEntity1 = userController.getUserById("1");

        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());
        assertEquals(userDto, responseEntity1.getBody());

        ResponseEntity<?> responseEntity2 = userController.getUserById("2");

        assertEquals(HttpStatus.NOT_FOUND, responseEntity2.getStatusCode());
    }

    @Test
    public void testDeleteUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId("1");
        userDto.setUsername("testUser");
        userDto.setScore(50);

        when(userService.deleteUser("1")).thenReturn(userDto);
        when(userService.deleteUser("2")).thenThrow(new UserNotFoundException("User not found"));

        ResponseEntity<?> responseEntity1 = userController.deleteUser("1");

        assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());
        assertEquals(userDto, responseEntity1.getBody());

        ResponseEntity<?> responseEntity2 = userController.deleteUser("2");

//        assertEquals(HttpStatus.NOT_FOUND, responseEntity2.getStatusCode());
//        assertEquals("User not found", responseEntity2.getBody());
    }
}
