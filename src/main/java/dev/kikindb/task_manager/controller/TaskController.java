package dev.kikindb.task_manager.controller;

import dev.kikindb.task_manager.entity.Task;
import dev.kikindb.task_manager.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@Slf4j
public class TaskController {

  @Autowired
  private TaskService taskService;

  @GetMapping
  public List<Task> getAllTasks() {
    return taskService.getAllTasks();
  }

  @PostMapping
  public ResponseEntity<Task> addTask(@RequestBody Task task) {
    log.info("Received Task: {}", task.toString());
    taskService.createTask(task);
    log.info("addTask:: Task created");
    return new ResponseEntity<>(task, HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Task> patchTask(@PathVariable String id, @RequestBody Task task) {
    // Check if the task has an ID
    if (id == null) {
      log.warn("patchTask:: No id provided");
      return ResponseEntity.badRequest().build(); // Bad request if no ID is provided
    }

    taskService.patchTask(id, task);
    log.info("patchTask:: Task patched");
    return ResponseEntity.ok(task);
  }
}
