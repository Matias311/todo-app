package com.todo.app;

import com.todo.app.service.ConnectionPostgresql;

/** Hello world. */
public class App {
  /**
   * Inicio de la aplicacion.
   *
   * @param args String
   */
  public static void main(String[] args) {
    new ConsoleComand();
    ConnectionPostgresql.INSTANCE.closeConnection();
  }
}
