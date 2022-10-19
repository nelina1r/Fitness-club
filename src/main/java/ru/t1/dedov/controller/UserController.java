package ru.t1.dedov.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.dedov.dto.UserDto;
import ru.t1.dedov.model.entity.User;
import ru.t1.dedov.service.interfaces.UserService;

import java.util.List;

@Api(value = "User controller")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("give user by id admin rights")
    @PostMapping("/user/give-admin/{id}")
    public ResponseEntity<String> giveUserAdminRights(@PathVariable Long id) {
        userService.giveUserAdminRights(id);
        return ResponseEntity.ok("user with id = " + id + " now admin");
    }

    @ApiOperation("find all users")
    @GetMapping("/user/list")
    public List<UserDto> findAllUsers(
            @Spec(path = "id", paramSeparator = ',', spec = Like.class) Specification<User> spec,
            @RequestParam(value = "search", required = false) String search,
            @PageableDefault Pageable page
    ) {
        return userService.getAllUsers(spec, search, page);
    }
}
