package com.example.springsession.controller;

import com.example.springsession.dto.MyRequestDTO;
import com.example.springsession.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
        System.out.println("inside UserController constructor");
    }

    @PostConstruct
    public void init()
    {
        System.out.println("inside UserController post constructor");
    }

    @Autowired
    private UserService userService;

    @PostMapping(path = "/update/employee/{id}")
    public boolean updateEmployee(@PathVariable String id, @RequestBody MyRequestDTO request)
    {
        return userService.updateEmployee(request, id);
    }
}
