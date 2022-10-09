package com.piotrzawada.Recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userService.userExist(user.email)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setRole("ROLE_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        userService.registerUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
