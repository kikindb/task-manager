package dev.kikindb.task_manager.controller;

import dev.kikindb.task_manager.entity.Task;
import dev.kikindb.task_manager.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private TaskService taskService;

  @GetMapping
  public List<Task> getAllTasks() {
    return taskService.getAllTasks();
  }

  @PostMapping
  public ResponseEntity<Task> addTask(@RequestBody Task task) {
    logger.info("Received Task: {}", task.toString());
    taskService.createTask(task);
    logger.info("addTask:: Task created");
    return new ResponseEntity<>(task, HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Task> patchTask(@PathVariable String id, @RequestBody Task task) {
    // Check if the task has an ID
    if (id == null) {
      logger.warn("patchTask:: No id provided");
      return ResponseEntity.badRequest().build(); // Bad request if no ID is provided
    }

    taskService.patchTask(id, task);
    logger.info("patchTask:: Task patched");
    return ResponseEntity.ok(task);
  }
}
