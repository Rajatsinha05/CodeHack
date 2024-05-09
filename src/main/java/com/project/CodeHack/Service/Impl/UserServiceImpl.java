package com.project.CodeHack.Service.Impl;

import com.project.CodeHack.DTO.UserDto;
import com.project.CodeHack.Model.UserAccount;
import com.project.CodeHack.Repository.UserRepository;
import com.project.CodeHack.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserAccount> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserAccount getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));
    }

    @Override
    public UserDto registerUser(UserAccount user) {
        UserAccount userData = userRepository.save(user);
        return mapUserToDto(userData);
    }

    @Override
    public UserAccount updateUserScore(String userId, int score) {
        UserAccount account = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));
        account.setScore(score);
        return userRepository.save(account);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    private UserDto mapUserToDto(UserAccount user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setScore(user.getScore());
        return userDto;
    }
}
