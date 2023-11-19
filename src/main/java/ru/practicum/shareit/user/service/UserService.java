package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User updateUser(User user, int userId);

    void removeUserById(int userId);

    User getUserById(int userId);

    List<User> getAllUsers();
}