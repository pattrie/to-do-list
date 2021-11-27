package com.github.pattrie.todolist.service;

import com.github.pattrie.todolist.model.Task;
import com.github.pattrie.todolist.model.json.TaskRequestJson;
import com.github.pattrie.todolist.model.json.TaskResponseJson;
import com.github.pattrie.todolist.repository.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;

  private final ModelMapper mapper = new ModelMapper();


  public TaskResponseJson createTask(TaskRequestJson taskRequestJson) {
    final Task task = mapper.map(taskRequestJson, Task.class);

    final Task save = taskRepository.save(task);

    return mapper.map(save, TaskResponseJson.class);
  }

  public List<TaskResponseJson> listAllTasks() {
    final List<Task> tasks = taskRepository.findAll();
    return tasks.stream().map(task -> mapper.map(task, TaskResponseJson.class))
        .collect(Collectors.toList());
  }

  public ResponseEntity<TaskResponseJson> findById(Long id) {
    return taskRepository.findById(id)
        .map(task -> ResponseEntity.ok().body(mapper.map(task, TaskResponseJson.class)))
        .orElse(ResponseEntity.notFound().build());
  }

  public ResponseEntity<TaskResponseJson> updateById(TaskRequestJson taskRequestJson, Long id) {
    final Task task = mapper.map(taskRequestJson, Task.class);

    return taskRepository.findById(id).map(taskToUpdate -> {
          taskToUpdate.setTitle(task.getTitle());
          taskToUpdate.setDescription(task.getDescription());
          taskToUpdate.setDeadLine(task.getDeadLine());

          Task updated = taskRepository.save(taskToUpdate);

          return ResponseEntity.ok().body(mapper.map(updated, TaskResponseJson.class));

        }).orElse(ResponseEntity.notFound().build());
  }

  public ResponseEntity<Object> deleteById(Long id) {
    return taskRepository.findById(id)
        .map(taskToDelete -> {
          taskRepository.delete(taskToDelete);
          return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
  }
}
