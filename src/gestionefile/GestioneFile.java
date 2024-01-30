package gestionefile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author MC
 * @version 12/01/23
 */
public class GestioneFile {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //1)LETTURA
        Lettore lettore = new Lettore("user.json");
        lettore.start();
        
        //2)ELABORAZIONE
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Inserisci l'username: ");
            String username = reader.readLine();
            
            System.out.println("Inserisci la password (Scrivila in maiuscolo): ");
            String password = reader.readLine();
            
            System.out.println("Per proteggere al meglio la tua password bisogna cifrarla");
            System.out.println("Inserisci la chiave di cifratura (Scrivila in maiuscolo): ");
            String chiave = reader.readLine();
            
            Matrice m = new Matrice(chiave);
            Vigenere v1 = new Vigenere(0, 12, 0, 12, m);
            Thread t1 = new Thread(v1);
            Vigenere v2 = new Vigenere(12, 26, 0, 12, m);
            Thread t2 = new Thread(v2);
            Vigenere v3 = new Vigenere(0, 12, 12, 26, m);
            Thread t3 = new Thread(v3);
            Vigenere v4 = new Vigenere(12, 26, 12, 26, m);
            Thread t4 = new Thread(v4);
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            try {
              t1.join();
              t2.join();
              t3.join();
              t4.join();
            } catch (InterruptedException e) {
                System.err.println("Errore durante l'esecuzione del thread");
            }
            
            try {
                String fraseCifrata = m.cifra(password);
                System.out.println("La tua password cifrata: " + fraseCifrata);
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Errore di indice nella matrice");
            }
            
            //3) SCRITTURA
            Scrittore scrittore = new Scrittore("output.csv");
            Thread threadScrittore = new Thread(scrittore);
            threadScrittore.start();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}