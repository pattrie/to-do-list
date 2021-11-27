package com.github.pattrie.todolist.model.json;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class TaskRequestJson {

  private String title;

  private String description;

  private LocalDateTime deadLine;
}
