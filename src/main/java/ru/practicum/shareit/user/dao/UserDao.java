package ru.practicum.shareit.user.dao;

import ru.practicum.shareit.user.User;

import java.util.List;

public interface UserDao {

    User getUserById(int userid);

    List<User> getAllUsers();

    User addUser(User user);

    User updateUser(User user, int userId);

    void removeUserById(User user);
}