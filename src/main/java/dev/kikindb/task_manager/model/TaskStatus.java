package dev.kikindb.task_manager.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {
  TODO(0),
  DOING(1),
  DONE(2),
  DELETED(3);

  private final int value;

  TaskStatus(int value) {
    this.value = value;
  }

  @JsonValue // Serialize the numeric value when returning the enum in JSON
  public int getValue() {
    return value;
  }

  // Convert numeric value to TaskStatus enum
  public static TaskStatus fromValue(int value) {
    for (TaskStatus status : TaskStatus.values()) {
      if (status.getValue() == value) {
        return status;
      }
    }
    throw new IllegalArgumentException("Unknown value: " + value);
  }
}