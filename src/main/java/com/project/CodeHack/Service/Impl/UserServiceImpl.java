package com.project.CodeHack.Service.Impl;

import com.project.CodeHack.DTO.UserDto;
import com.project.CodeHack.Model.UserAccount;
import com.project.CodeHack.Repository.UserRepository;
import com.project.CodeHack.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        List<UserAccount> userAccounts = userRepository.findAll();
        return mapUserAccountsToDtos(userAccounts);
    }

    @Override
    public UserDto getUserById(String userId) {
        UserAccount userAccount = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));
        return mapUserAccountToDto(userAccount);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        UserAccount userAccount = mapDtoToUserAccount(userDto);
        UserAccount savedUserAccount = userRepository.save(userAccount);
        return mapUserAccountToDto(savedUserAccount);
    }

    @Override
    public UserDto updateUserScore(String userId, int score) {
        UserAccount userAccount = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));
        userAccount.setScore(score);
        UserAccount updatedUserAccount = userRepository.save(userAccount);
        return mapUserAccountToDto(updatedUserAccount);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    // Helper methods to map between DTOs and domain objects

    private UserDto mapUserAccountToDto(UserAccount userAccount) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userAccount.getUserId());
        userDto.setUsername(userAccount.getUsername());
        userDto.setScore(userAccount.getScore());
        return userDto;
    }

    private List<UserDto> mapUserAccountsToDtos(List<UserAccount> userAccounts) {
        return userAccounts.stream()
                .map(this::mapUserAccountToDto)
                .collect(Collectors.toList());
    }

    private UserAccount mapDtoToUserAccount(UserDto userDto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userDto.getUserId());
        userAccount.setUsername(userDto.getUsername());
        userAccount.setScore(userDto.getScore());
        return userAccount;
    }
}
