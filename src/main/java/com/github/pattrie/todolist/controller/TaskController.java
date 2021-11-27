package com.github.pattrie.todolist.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.github.pattrie.todolist.model.json.TaskRequestJson;
import com.github.pattrie.todolist.model.json.TaskResponseJson;
import com.github.pattrie.todolist.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Slf4j
public class TaskController {

  private final TaskService taskService;

  @ApiOperation(value = "Creating a new task")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Successfully created task"),
      @ApiResponse(code = 500, message = "There was an error creating the task, check the information")
  })
  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public TaskResponseJson createTask(@RequestBody TaskRequestJson taskRequestJson) {
    log.info("Creating a new task with the information:: {}", taskRequestJson);
    return taskService.createTask(taskRequestJson);
  }

  @ApiOperation(value = "Listing all tasks")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully listed tasks"),
      @ApiResponse(code = 500, message = "There was an error listing the tasks, check the information")
  })
  @GetMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<TaskResponseJson> getAllTasks() {
    log.info("Listing all tasks.");
    return taskService.listAllTasks();
  }

  @ApiOperation(value = "Searching task")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully found task"),
      @ApiResponse(code = 404, message = "Task not found with this id")
  })
  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<TaskResponseJson> getById(@PathVariable Long id) {
    log.info("Searching task by ID:: {}", id);
    return taskService.findById(id);
  }

  @ApiOperation(value = "Updating task")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully updated task"),
      @ApiResponse(code = 404, message = "Task not found with this id")
  })
  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<TaskResponseJson> updateById(@PathVariable Long id,
      @RequestBody TaskRequestJson taskRequestJson) {
    log.info("Updating task with ID:: {} - New information:: {}", id, taskRequestJson);
    return taskService.updateById(taskRequestJson, id);
  }

  @ApiOperation(value = "Deleting task")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Successfully deleted task"),
      @ApiResponse(code = 404, message = "Task not found with this id")
  })
  @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Object> deleteById(@PathVariable Long id) {
    log.info("Deleting task with ID:: {}", id);
    return taskService.deleteById(id);
  }
}