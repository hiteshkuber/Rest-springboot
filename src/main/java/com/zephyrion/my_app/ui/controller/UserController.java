package com.zephyrion.my_app.ui.controller;

import com.zephyrion.my_app.ui.model.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController // Receive http request and match the url path
@RequestMapping("users") // Controller responsible for given path. http://localhost::8080/users
public class UserController {

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE) // Binds below method with http get request // Path param
    public ResponseEntity getUser(@PathVariable String userId) {
        UserRest userRest = new UserRest();
        userRest.setUserId(123);
        userRest.setName(userId);

        return new ResponseEntity<UserRest>(userRest, HttpStatus.CREATED);
    }

    @GetMapping // Binds below method with http get request // Query param
    public String getUser(@RequestParam(value = "start") int start, @RequestParam(value = "end", defaultValue = "0") int end) {
        return "get user was called : A : " + start + " : " + end;
    }

    @PostMapping
    public String createUser() {
        return "create user was called : B";
    }

    @PutMapping
    public String updateUser() {
        return "update user was called : C";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user was called : D";
    }
}
