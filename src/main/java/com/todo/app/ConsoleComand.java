package com.todo.app;

import com.todo.app.model.Task;
import com.todo.app.repository.TaskRepositoryImpl;
import com.todo.app.service.TaskService;
import java.util.Map;
import java.util.Scanner;

/** ConsoleComand. */
public class ConsoleComand {
  private String welcomeMessage =
      "Welcome!\n1. Create a new task\n2. Update a task\n3. Delete a task\n4. Show tasks\n5. Exit";
  private final TaskService service = new TaskService(new TaskRepositoryImpl());
  private Map<Integer, Task> taskList;

  /** Start the "layout". */
  public ConsoleComand() {
    System.out.println(welcomeMessage);

    try {
      taskList = service.readAllTask();
    } catch (Exception e) {
      // Nothing here because we need to obtain the tasks first
    }

    try (Scanner sc = new Scanner(System.in); ) {
      boolean running = true;
      while (running) {
        int userSelection = Integer.parseInt(sc.nextLine());

        switch (userSelection) {
          case 1:
            System.out.println("What's the task's name?");
            String taskName = sc.nextLine();
            service.createTask(taskName);
            System.out.println("Task created successfully");
            break;
          case 2:
            if (taskList.isEmpty() || taskList == null) {
              System.out.println("Error there are no tasks");
            } else {

              taskList.values().forEach(System.out::println);
              int idTaskChange =
                  menuUser("Please select the task to change with the task's id", sc);

              System.out.println("What's the new tasks name?");
              String newNameTask = sc.nextLine();

              service.updateTask(idTaskChange, newNameTask);
              System.out.println("Change done");
            }
            break;
          case 3:
            if (taskList.isEmpty() || taskList == null) {
              System.out.println("Error there are no tasks");
              break;
            } else {
              taskList.values().forEach(System.out::println);
              int idTaskDelete = menuUser("Please select the task to delete with the id", sc);
              service.deleteTask(idTaskDelete);
              System.out.println("Task deleted");
            }

            break;
          case 4:
            taskList = service.readAllTask();
            if (!taskList.isEmpty()) {
              taskList.values().forEach(System.out::println);
            }
            break;
          case 5:
            running = false;
            System.out.println("bye");
            break;

          default:
            System.out.println("Wrong answer, do it again!");
            break;
        }
      }
    } catch (Exception e) {
      System.out.println("Error in the execution: " + e);
    }
  }

  /**
   * We pass a message for the user to see and he can chose a option or pass a value.
   *
   * @param message String
   * @param sc Scanner
   * @return int
   */
  public int menuUser(String message, Scanner sc) {
    System.out.println(message);
    return Integer.parseInt(sc.nextLine());
  }
}
