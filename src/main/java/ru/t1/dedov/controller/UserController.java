package ru.t1.dedov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.UserDto;
import ru.t1.dedov.service.interfaces.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/give-admin/{id}")
    public ResponseEntity<String> giveUserAdminRights(@PathVariable Long id){
        userService.giveUserAdminRights(id);
        return ResponseEntity.ok("user with id = " + id + " now admin");
    }

    @GetMapping("/user/list")
    public List<UserDto> findAll(){
        return userService.getAllUsers();
    }
}
