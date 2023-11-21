package ru.practicum.shareit.user.dao;

import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserDao {

    User getUserById(int userid);

    List<UserDto> getAllUsers();

    UserDto addUser(User user);

    UserDto updateUser(User user, int userId);

    void removeUserById(User user);
}