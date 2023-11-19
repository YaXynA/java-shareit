package ru.practicum.shareit.user;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @EqualsAndHashCode.Include
    private int id;

    @NotBlank(message = "Name cannot be empty or contain spaces.")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must contain the character @")
    private String email;
}