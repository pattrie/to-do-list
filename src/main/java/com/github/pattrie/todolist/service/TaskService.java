package com.github.pattrie.todolist.service;

import com.github.pattrie.todolist.model.Task;
import com.github.pattrie.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> listAllTasks() {
        return taskRepository.findAll();
    }

    public ResponseEntity<Task> findById(Long id) {
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Task> updateById(Task task, Long id)  {
        return taskRepository.findById(id)
            .map(taskToUpdate -> {
                taskToUpdate.setTitle(task.getTitle());
                taskToUpdate.setDescription(task.getDescription());
                taskToUpdate.setDeadLine(task.getDeadLine());
                Task updated = taskRepository.save(taskToUpdate);
                return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteById(Long id)  {
        return taskRepository.findById(id)
            .map(taskToDelete -> {
              taskRepository.delete(taskToDelete);
              return ResponseEntity.noContent().build();
            }).orElse(ResponseEntity.notFound().build());
    }
}
