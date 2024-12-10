package dev.kikindb.task_manager.controller;

import dev.kikindb.task_manager.entity.User;
import dev.kikindb.task_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping
  public ResponseEntity<User> addUser(@RequestBody User user) {
    userService.createUser(user);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

}
