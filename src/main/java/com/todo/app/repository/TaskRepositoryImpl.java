package com.todo.app.repository;

import com.todo.app.model.Task;
import com.todo.app.service.ConnectionPostgresql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/** TaskRepositoryImpl. */
public class TaskRepositoryImpl implements TaskRepository {
  private Connection conn = ConnectionPostgresql.INSTANCE.getConnection();

  @Override
  public void createTask(String nameTask) {
    String query = "insert into task (name) values (?);";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, nameTask);
      stmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Error creating the task " + e);
    }
  }

  @Override
  public void deleteTask(int id) {
    String query = "delete from task where id = ?;";
    try (PreparedStatement prestmt = conn.prepareStatement(query)) {
      prestmt.setInt(1, id);
      prestmt.execute();
    } catch (SQLException e) {
      System.out.println("Error deleting the task " + e);
    }
  }

  @Override
  public void updateTask(int id, String newNameTask) {
    String query = "update task set name=? where id=?;";
    try (PreparedStatement prestmt = conn.prepareStatement(query)) {
      prestmt.setString(1, newNameTask);
      prestmt.setInt(2, id);
      prestmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Error updating the task " + id + " error: " + e);
    }
  }

  @Override
  public Map<Integer, Task> readAllTasks() {
    String query = "select * from task;";
    Map<Integer, Task> taskList = new HashMap<>();

    try (Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query)) {
      while (result.next()) {
        // formate time (YYYY-MM-DD HH(24-hour format):MI:SS:US:TZ
        Task task =
            Task.createTask(
                result.getInt("id"), result.getString("name"), result.getString("date"));
        taskList.put(result.getInt("id"), task);
      }
    } catch (SQLException e) {
      System.out.println("There are no tasks");
    }
    return taskList;
  }
}
