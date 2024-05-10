package com.project.CodeHack.Service;


import com.project.CodeHack.DTO.UserDto;
import com.project.CodeHack.Model.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(String userId);
    UserDto registerUser(UserDto UserDto);
    UserDto updateUserScore(String userId, int score);
    UserDto deleteUser(String userId);
}
