package com.github.pattrie.todolist.model;

import com.github.pattrie.todolist.model.json.TaskResponseJson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskToTaskResponseJsonConverter implements Converter<Task, TaskResponseJson> {

  @Override
  public TaskResponseJson convert(final Task task) {
    return TaskResponseJson.builder()
        .id(task.getId())
        .title(task.getTitle())
        .description(task.getDescription())
        .deadLine(task.getDeadLine())
        .updatedAt(task.getUpdatedAt())
        .createdAt(task.getCreatedAt())
        .build();
  }
}
