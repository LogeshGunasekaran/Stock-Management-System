package com.product_department.controller;

import com.product_department.dto.UserDto;
import com.product_department.entity.UserCredential;
import com.product_department.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-account")
    public ResponseEntity<String> newAccount(@RequestBody UserDto userDto)
    {
        System.out.println(userDto.getPassword()+" "+userDto.getName());
        return new ResponseEntity<>(userService.createAccount(userDto), HttpStatus.CREATED);
    }
}
