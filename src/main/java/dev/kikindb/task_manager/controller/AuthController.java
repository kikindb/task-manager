package dev.kikindb.task_manager.controller;

import dev.kikindb.task_manager.entity.User;
import dev.kikindb.task_manager.exception.ErrorResponse;
import dev.kikindb.task_manager.exception.UserNotFoundException;
import dev.kikindb.task_manager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping
  public ResponseEntity<?> authenticate(@RequestBody User user) {
    try {
      // Validate the request body
      if (user.getEmail() == null || user.getPassword() == null) {
        return ResponseEntity.badRequest().body(new ErrorResponse("Email and password are required."));
      }

      // Authenticate the user
      Optional<User> authUser = authService.authenticate(user);

      if (authUser.isEmpty()) {
        throw new UserNotFoundException("Incorrect Credentials");
      }

      // Generate or retrieve the token (this is just an example token)
      String token = "example-auth-token";

      // Create HttpHeaders and add the custom 'x-auth-token' header
      HttpHeaders headers = new HttpHeaders();
      headers.set("x-auth-token", token);

      // Return the ResponseEntity with the authenticated user and headers
      return new ResponseEntity<>(authUser.get(), headers, HttpStatus.OK);

    } catch (UserNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
    } catch (Exception ex) {
      // Log the exception (consider using a logging framework like SLF4J)
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ErrorResponse("An unexpected error occurred: " + ex.getMessage()));
    }
  }


}
