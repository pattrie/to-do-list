package com.github.pattrie.todolist.controller;

import com.github.pattrie.todolist.model.Task;
import com.github.pattrie.todolist.service.TaskService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
@Slf4j
public class TaskController {

  private TaskService taskService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Task createTask(@RequestBody Task task) {
    log.info("Creating a new task with the information:: {}", task);
    return taskService.createTask(task);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Task> getAllTasks() {
    log.info("Listing all tasks.");
    return taskService.listAllTasks();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Task> getById(@PathVariable Long id) {
    log.info("Searching task by ID:: {}", id);
    return taskService.findById(id);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Task> updateById(@PathVariable Long id, @RequestBody Task task) {
    log.info("Updating task with ID:: {} - New information:: {}", id, task);
    return taskService.updateById(task, id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Object> deleteById(@PathVariable Long id) {
    log.info("Deleting task with ID:: {}", id);
    return taskService.deleteById(id);
  }
}