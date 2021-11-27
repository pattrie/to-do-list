package com.github.pattrie.todolist.model.json;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class TaskResponseJson {
  private Long id;

  private String title;

  private String description;

  private LocalDateTime deadLine;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
