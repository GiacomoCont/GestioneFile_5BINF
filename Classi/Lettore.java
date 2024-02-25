package gestionefile;

import java.io.*;

/**
 * @author Giacomo Contini
 */

public class Lettore extends Thread {
  String nomeFile;
  
  /**
   * Costruttore della classe Lettore.
   * @param nomeFile Il nome del file da leggere.
  */

  public Lettore(String nomeFile) {
    this.nomeFile = nomeFile;
  }

  /**
   * Legge il file senza tener conto del tipo di file e lo mostra in output
   */
  public void leggi() {
    // Utilizzo del try-with-resources per garantire la chiusura automatica del file
    try (BufferedReader fr = new BufferedReader(new FileReader(nomeFile))) {
      // Leggo carattere per carattere e lo stampo
      int i;
      while ((i = fr.read()) != -1)
        System.out.print((char) i);

      System.out.print("\n\r");
    } catch (IOException ex) {
      System.err.println("Errore in lettura! " + ex.getMessage());
    }
  }

  // Metodo per leggere i file utilizzando gli Stream
  public String leggiStream(String nomeFile) {
    StringBuilder fileContent = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        fileContent.append(line);
      }
    } catch (IOException ex) {
      System.err.println("Errore in lettura! " + ex.getMessage());
    }
    return fileContent.toString();
  }

  @Override
  public void run() {
    leggi();
  }
}