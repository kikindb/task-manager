package dev.kikindb.task_manager.controller;

import dev.kikindb.task_manager.entity.Task;
import dev.kikindb.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @GetMapping
  public List<Task> getAllTasks() {
    return taskService.getAllTasks();
  }

  @PostMapping
  public ResponseEntity<Task> addTask(@RequestBody Task task) {
    System.out.println("Received Task: " + task.toString());
    taskService.createTask(task);
    return new ResponseEntity<>(task, HttpStatus.OK);
  }

  @PatchMapping
  public ResponseEntity<Task> patchTask(@RequestBody Task task) {
    // Check if the task has an ID
    if (task.getId() == null) {
      return ResponseEntity.badRequest().build(); // Bad request if no ID is provided
    }

    taskService.patchTask(task);
    return ResponseEntity.ok(task);
  }
}
