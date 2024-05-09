package com.project.CodeHack.DTO;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private String userId;
    private String username;
    private int score;
    private Set<String> badges = new HashSet<>();
}
