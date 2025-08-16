package com.todo.app.repository;

import com.todo.app.model.Task;
import java.util.Map;

/** TaskRepository. Interface with the CRUD (create, delete, update, read) */
public interface TaskRepository {
  /**
   * CREATE (create a task with the name / all the logic is in the implementation).
   *
   * @param nameTask String
   */
  void createTask(String nameTask);

  /**
   * DELETE (delete a task with a id).
   *
   * @param id int
   */
  void deleteTask(int id);

  /**
   * UPDATE (update a task with a id).
   *
   * @param newNameTask String
   * @param id int
   */
  void updateTask(int id, String newNameTask);

  /**
   * READ (read all the tasks).
   *
   * @return List
   */
  Map<Integer, Task> readAllTasks();
}
