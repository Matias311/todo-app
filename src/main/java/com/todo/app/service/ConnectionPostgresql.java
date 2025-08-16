package com.todo.app.service;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Enum to manage the Connection. ConnectionPostgresql. */
public enum ConnectionPostgresql {
  INSTANCE;

  private Connection conn;

  private ConnectionPostgresql() {
    try {
      Dotenv dotenv = Dotenv.configure().load();
      this.conn = DriverManager.getConnection(dotenv.get("DB_URL"));
    } catch (SQLException e) {
      System.out.println("Error in low level " + e);
    }
  }

  /**
   * Return a Connection to sql.
   *
   * @return Connection
   */
  public Connection getConnection() {
    return conn;
  }

  /** Close the Connection, only use at the final of the aplication. */
  public void closeConnection() {
    try {
      if (conn != null && !conn.isClosed()) {
        conn.close();
      }
    } catch (SQLException e) {
      System.out.println("Error in low level for closing connection " + e);
    }
  }
}
