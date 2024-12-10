package dev.kikindb.task_manager.repository;

import dev.kikindb.task_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}