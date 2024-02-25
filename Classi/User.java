package gestionefile;

/**
 * @author Giacomo Contini
 */

import java.io.*;

public class User implements Serializable {
  private String username;
  private String password;
  
  /**
   * Costruttore della classe User.
   * @param username L'username dell'utente.
   * @param password La password dell'utente.
  */

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}