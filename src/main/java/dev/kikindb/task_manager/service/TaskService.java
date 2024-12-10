package dev.kikindb.task_manager.service;

import dev.kikindb.task_manager.entity.Task;
import dev.kikindb.task_manager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public void createTask (Task task) {
    task.setCreatedAt(LocalDateTime.now());
    task.setUpdatedAt(LocalDateTime.now());
    taskRepository.save(task);
  }

  public void patchTask(Task task) {
    // Retrieve the existing task from the database
    Optional<Task> existingTaskOptional = taskRepository.findById(String.valueOf(task.getId()));

    if (existingTaskOptional.isPresent()) {
      Task existingTask = existingTaskOptional.get();

      // Update fields only if they are not null
      if (task.getTitle() != null) {
        existingTask.setTitle(task.getTitle());
      }
      if (task.getBody() != null) {
        existingTask.setBody(task.getBody());
      }
      if (task.getAuthorName() != null) {
        existingTask.setAuthorName(task.getAuthorName());
      }
      if (task.getStatus() != 0) {  // Assuming 0 is an invalid status for patch
        existingTask.setStatus(task.getStatus());
      }
      if (task.getUserId() != null) {
        existingTask.setUserId(task.getUserId());
      }
      if (task.getCreatedAt() != null) {
        existingTask.setCreatedAt(task.getCreatedAt());
      }
      if (task.getUpdatedAt() != null) {
        existingTask.setUpdatedAt(task.getUpdatedAt());
      }

      // Save the updated task back to the database
      taskRepository.save(existingTask);
    } else {
      throw new RuntimeException("Task not found with id: " + task.getId());
    }
  }
}
