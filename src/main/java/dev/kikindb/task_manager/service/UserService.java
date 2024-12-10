package dev.kikindb.task_manager.service;

import dev.kikindb.task_manager.entity.User;
import dev.kikindb.task_manager.model.Role;
import dev.kikindb.task_manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public void createUser(User user) {
    user.setCreatedAt(LocalDateTime.now());
    user.setUpdatedAt(LocalDateTime.now());
    user.setRole(Role.USER);
    userRepository.save(user);
  }
}
