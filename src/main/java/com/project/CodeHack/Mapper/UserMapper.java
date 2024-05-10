package com.project.CodeHack.Mapper;


import com.project.CodeHack.DTO.UserDto;
import com.project.CodeHack.Model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapUserAccountToDto(UserAccount userAccount) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userAccount.getUserId());
        userDto.setUsername(userAccount.getUsername());
        userDto.setScore(userAccount.getScore());
        userDto.setBadges(userAccount.getBadges());
        return userDto;
    }

    public UserAccount mapDtoToUserAccount(UserDto userDto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userDto.getUserId());
        userAccount.setUsername(userDto.getUsername());
        userAccount.setScore(userDto.getScore());
        userAccount.setBadges(userDto.getBadges());
        return userAccount;
    }
}

