package com.zephyrion.my_app.ui.controller;

import com.zephyrion.my_app.ui.model.request.UserRequestModel;
import com.zephyrion.my_app.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController // Receive http request and match the url path
@RequestMapping("users") // Controller responsible for given path. http://localhost::8080/users
public class UserController {

    Map<String, UserRest> users;

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE) // Binds below method with http get request // Path param
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

        if (users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping // Binds below method with http get request // Query param
    public String getUser(@RequestParam(value = "start") int start, @RequestParam(value = "end", defaultValue = "0") int end) {
        return "get user was called : A : " + start + " : " + end;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRest> createUser(@RequestBody UserRequestModel userRequestModel) {

        UserRest userRest = new UserRest();
        userRest.setUserId(userRequestModel.getUserId());
        userRest.setName(userRequestModel.getName());

        String userID = UUID.randomUUID().toString();
        if(users == null) {
            users = new HashMap<>();
        }
        userRest.setUUID(userID);
        users.put(userID, userRest);

        return new ResponseEntity<>(userRest, HttpStatus.CREATED);
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
