package dev.kikindb.task_manager.controller;

import dev.kikindb.task_manager.model.HelloWorld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

  @GetMapping
  public HelloWorld helloWorld() {
    return new HelloWorld("Hello World");
  }
}
