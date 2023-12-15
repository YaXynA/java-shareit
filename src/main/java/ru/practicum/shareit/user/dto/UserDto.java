package ru.practicum.shareit.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserDto {

    private int id;

    @NotBlank(message = "Login cannot be empty or contain spaces.")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must contain the character @")
    private String email;
}