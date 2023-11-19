package ru.practicum.shareit.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dao.UserDao;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserDao repository;

    @Override
    public User addUser(User user) {
        return repository.addUser(user);
    }

    @Override
    public User updateUser(User user, int userId) {

        repository.getUserById(userId);
        user.setId(userId);
        return repository.updateUser(user, userId);
    }

    @Override
    public void removeUserById(int userId) {
        repository.removeUserById(repository.getUserById(userId));
    }

    @Override
    public User getUserById(int userId) {
        return repository.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }
}