package ru.practicum.shareit.user.dao;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception.EmailDuplicateException;
import ru.practicum.shareit.exception.NoDataFoundException;
import ru.practicum.shareit.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static int newId = 1;

    private final HashMap<Integer, User> userMap = new HashMap<>();

    @Override
    public User getUserById(int userId) {

        if (userMap.containsKey(userId)) {
            return userMap.get(userId);
        } else {
            throw new NoDataFoundException("User id " + userId + " not found.");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User addUser(User user) {

        for (User userCheckEmail : getAllUsers()) {
            if (userCheckEmail.getEmail().equals(user.getEmail())) {
                throw new EmailDuplicateException("there is already a user with an email " + user.getEmail());
            }
        }

        if (user.getId() == 0) {
            user.setId(newId++);
        }

        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user, int userId) {

        User newUser = userMap.get(userId);

        if (user.getName() != null) {
            newUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            for (User userCheckEmail : getAllUsers()) {
                if (userCheckEmail.getEmail().equals(user.getEmail()) && userCheckEmail.getId() != userId) {
                    throw new EmailDuplicateException("there is already a user with an email " + user.getEmail());
                }
            }

            newUser.setEmail(user.getEmail());
        }

        userMap.put(userId, newUser);
        return userMap.get(user.getId());
    }

    @Override
    public void removeUserById(User user) {

        if (!userMap.containsValue(user)) {
            throw new NoDataFoundException("User id " + user.getId() + " not found.");
        }

        userMap.remove(user.getId());
    }
}