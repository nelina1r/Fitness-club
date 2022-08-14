package ru.t1.dedov.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.dedov.dto.AuthenticationRequestDto;
import ru.t1.dedov.dto.PersonRegistrationDto;
import ru.t1.dedov.exceptions.InvalidTypeException;
import ru.t1.dedov.security.jwt.JwtTokenProvider;
import ru.t1.dedov.service.interfaces.UserService;

import java.util.Map;

@Api(value = "Auth controller")
@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @ApiOperation("register new employee/client")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody PersonRegistrationDto personRegistrationDto) throws InvalidTypeException {
        userService.register(personRegistrationDto);
        return ResponseEntity.ok("registered");
    }

    @ApiOperation("login")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            if (userService.findByUsername(requestDto.getUsername()) == null) {
                throw new UsernameNotFoundException("User with username: " + requestDto.getUsername() + " not found");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
            return ResponseEntity.ok(Map.of("username", requestDto.getUsername(),
                                            "token", jwtTokenProvider.createToken(requestDto.getUsername())));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}