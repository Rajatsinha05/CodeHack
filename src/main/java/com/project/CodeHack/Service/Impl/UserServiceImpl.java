package com.project.CodeHack.Service.Impl;

import com.project.CodeHack.DTO.UserDto;
import com.project.CodeHack.Mapper.UserMapper;
import com.project.CodeHack.Model.UserAccount;
import com.project.CodeHack.Repository.UserRepository;
import com.project.CodeHack.Service.UserService;
import com.project.CodeHack.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<UserAccount> userAccounts = userRepository.findAll();
        return userAccounts.stream()
                .map(userMapper::mapUserAccountToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(String userId) {
        UserAccount userAccount = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.mapUserAccountToDto(userAccount);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        UserAccount userAccount = userMapper.mapDtoToUserAccount(userDto);
        UserAccount savedUserAccount = userRepository.save(userAccount);
        return userMapper.mapUserAccountToDto(savedUserAccount);
    }

    @Override
    public UserDto updateUserScore(String userId, int score) {
        UserAccount user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        user.setScore(score);
        UserAccount updatedUser = updateBadges(user);
        UserAccount savedUser = userRepository.save(updatedUser);
        return userMapper.mapUserAccountToDto(savedUser);
    }

    @Override
    public UserDto deleteUser(String userId) {
        UserAccount userAccount = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.deleteById(userId);
        return userMapper.mapUserAccountToDto(userAccount);
    }

    private UserAccount updateBadges(UserAccount user) {
        int newScore = user.getScore();
        Set<String> badges = user.getBadges();

        if (newScore >= 60) {
            badges.add("Code Master");
        } else if (newScore >= 30) {
            badges.add("Code Champ");
        } else if (newScore >= 1) {
            badges.add("Code Ninja");
        }

        if (badges.size() > 3) {
            throw new IllegalArgumentException("User can only have a maximum of three unique badges");
        }
        user.setBadges(badges);
        return user;
    }
}
