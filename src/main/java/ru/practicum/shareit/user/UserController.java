package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserServiceImpl;


import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper mapper;

    @PostMapping
    public UserDto addUser(@RequestBody @Valid UserDto userDto) {

        User user = mapper.returnUser(userDto);
        userService.addUser(user);
        log.info("Add User {} ", user.getId());
        return mapper.returnUserDto(user);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId) {

        User user = mapper.returnUser(userDto);
        User newUser = userService.updateUser(user, userId);
        log.info("Update User {} ", newUser.getId());
        return mapper.returnUserDto(newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) {

        log.info("User {} deleted ", userId);
        userService.removeUserById(userId);
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Integer userId) {

        log.info("Get User {} ", userId);
        return mapper.returnUserDto(userService.getUserById(userId));
    }

    @GetMapping
    public List<UserDto> getAllUsers() {

        log.info("List all Users");
        return userService.getAllUsers()
                .stream()
                .map(mapper::returnUserDto)
                .collect(toList());
    }
}