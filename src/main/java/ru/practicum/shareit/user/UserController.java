package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDto addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable(name = "id") int userId) {
        return userService.getUserDtoById(userId);
    }

    @DeleteMapping("/{id}")
    public UserDto removeUserById(@PathVariable(name = "id") int userId) {
        return userService.removeUserById(userId);
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        return userService.updateUser(id, fields);
    }
}