package gestionefile;

/**
 * @author Giacomo Contini
 */

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scrittore implements Runnable {

  String nomeFile;
  String username;
  String password;
  
  /**
   * Costruttore della classe Scrittore.
   * @param nomeFile Il nome del file su cui scrivere le informazioni.
   * @param username Lo username da scrivere nel file.
   * @param password La password da scrivere nel file.
  */

  public Scrittore(String nomeFile, String username, String password) {
    this.nomeFile = nomeFile;
    this.username = username;
    this.password = password;
  }
  public void scrivi() {
    //Utilizzo del try-with-resources per garantire la chiusura automatica del file
    try (BufferedWriter br = new BufferedWriter(new FileWriter(nomeFile))) {
      // Scrivo nel buffer
      br.write("<" + username + ">");
      br.write("\n\r");
      br.write("<" + password + ">");
      br.write("\n\r");
      // Svuoto il buffer e salvo nel file i dati
      br.flush();
    } catch (IOException ex) {
      Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  @Override
  public void run() {
    scrivi();
  }
}