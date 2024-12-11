package dev.kikindb.task_manager.controller;

import dev.kikindb.task_manager.entity.User;
import dev.kikindb.task_manager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping
  public ResponseEntity<User> authenticate(@RequestBody User user) {
    // Generate or retrieve your token (this is just an example token)
    String token = "example-auth-token";

    // Create HttpHeaders and add the custom 'x-auth-token' header
    HttpHeaders headers = new HttpHeaders();
    headers.set("x-auth-token", token);

    Optional<User> authUser = authService.authenticate(user);

    // Return the ResponseEntity with the user and headers
    return new ResponseEntity<>(authUser.get(), headers, HttpStatus.OK);
  }
}
