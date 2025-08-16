package com.todo.app.service;

import com.todo.app.model.Task;
import com.todo.app.repository.TaskRepository;
import java.util.Map;

/** TaskService. */
public class TaskService {
  private TaskRepository repository;

  /**
   * We pass the implementation.
   *
   * @param repository TaskRepository
   */
  public TaskService(TaskRepository repository) {
    this.repository = repository;
  }

  /**
   * Creates a task in db.
   *
   * @param nameTask String
   */
  public void createTask(String nameTask) {
    if (!nameTask.isEmpty()) {
      repository.createTask(nameTask);
    }
  }

  /**
   * Delete a task using the id.
   *
   * @param id int
   */
  public void deleteTask(int id) {
    if (id >= 0) {
      repository.deleteTask(id);
    }
  }

  /**
   * Update the task using the name and we pass the new task name.
   *
   * @param id int
   * @param newNameTask String
   */
  public void updateTask(int id, String newNameTask) {
    if (id >= 0 && !newNameTask.isEmpty()) {
      repository.updateTask(id, newNameTask);
    }
  }

  /**
   * Method to obtain the task save in db.
   *
   * @return Map
   */
  public Map<Integer, Task> readAllTask() {
    return repository.readAllTasks();
  }
}
