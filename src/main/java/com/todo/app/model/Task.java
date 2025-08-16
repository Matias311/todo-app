package com.todo.app.model;

/** Task. */
public class Task {

  private String nameTask;
  private final int id;
  private final String datetime;

  private Task(int id, String nameTask, String datetime) {
    this.id = id;
    this.nameTask = nameTask;
    this.datetime = datetime;
  }

  /**
   * Creates a task with all the data.
   *
   * @param id int
   * @param nameTask String
   * @param datetime String
   * @return Task
   */
  public static Task createTask(int id, String nameTask, String datetime) {
    return new Task(id, nameTask, datetime);
  }

  public String getNameTask() {
    return this.nameTask;
  }

  public void setNameTask(String nameTask) {
    this.nameTask = nameTask;
  }

  public String getDate() {
    return this.datetime;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    String text = String.format("ID: %s NAME: %s DATE: %s", this.id, this.nameTask, this.datetime);
    return text;
  }
}
