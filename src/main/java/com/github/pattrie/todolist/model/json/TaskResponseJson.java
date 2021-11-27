package com.github.pattrie.todolist.model.json;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskResponseJson {
  private Long id;

  private String title;

  private String description;

  private LocalDateTime deadLine;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
