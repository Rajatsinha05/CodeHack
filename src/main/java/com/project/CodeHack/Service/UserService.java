package com.project.CodeHack.Service;


import com.project.CodeHack.DTO.UserDto;
import com.project.CodeHack.Model.UserAccount;

import java.util.List;

public interface UserService {
    List<UserAccount> getAllUsers();
    UserAccount getUserById(String userId);
    UserAccount registerUser(UserDto userDto);
    UserAccount updateUserScore(String userId, int score);
    void deleteUser(String userId);
}
