package com.project.CodeHack.Service;


import com.project.CodeHack.DTO.UserDto;
import com.project.CodeHack.Model.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserAccount> getAllUsers();
    UserAccount getUserById(String userId);
    UserDto registerUser(UserAccount userAccount);
    UserAccount updateUserScore(String userId, int score);
    void deleteUser(String userId);
}
