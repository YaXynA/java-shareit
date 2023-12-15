package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto addUser(User user);

    UserDto updateUser(User user, int userId);

    void removeUserById(int userId);

    UserDto getUserById(int userId);

    List<UserDto> getAllUsers();
}