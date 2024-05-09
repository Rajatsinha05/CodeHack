package com.project.CodeHack.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "CodeHack")
public class UserAccount {
    @Id
    private String id;
    @NotBlank
    private String username;
    @Min(value = 0, message = "Score must be greater than or equal to 0")
    @Max(100)
    private int score;
    private Set<String> badges = new HashSet<>();

}




