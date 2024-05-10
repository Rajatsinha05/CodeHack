

package com.project.CodeHack.Controller;

import com.project.CodeHack.DTO.UserDto;
import com.project.CodeHack.Service.UserService;
import com.project.CodeHack.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto user = userService.registerUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable String userId) {
        try {
            UserDto userDto = userService.getUserById(userId);
            return ResponseEntity.ok(userDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserScore(@PathVariable String userId, @RequestBody UserDto userDto) {
        try {
            UserDto updatedUser = userService.updateUserScore(userId, userDto.getScore());
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable String userId) {
        try {
            UserDto deletedUser = userService.deleteUser(userId);
            return ResponseEntity.ok(deletedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }
}

//GET /users - Retrieve a list of all registered users
//GET /users/{userId} - Retrieve the details of a specific user
//POST /users - Register a new user to the contest
//PUT /users/{userId} - Update the score of a specific user
//DELETE /users/{userId} - Deregister a specific user from the contest
