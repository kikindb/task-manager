package dev.kikindb.task_manager.service;

import dev.kikindb.task_manager.entity.User;
import dev.kikindb.task_manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

  private final UserRepository userRepository;

  public AuthService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  public Optional<User> authenticate(User user) {
    Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
    if(byEmail.isEmpty()) return Optional.empty();

    if (byEmail.get().getPassword().equals(user.getPassword())) {
      return byEmail;
    }

    return Optional.empty();
  }
}
