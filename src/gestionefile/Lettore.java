package gestionefile;

import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author MC
 * @ 12/01/23
 */

public class Lettore extends Thread{
    String nomeFile;
    
    public Lettore(String nomeFile){
        this.nomeFile = nomeFile;
    }
    
    /*
    @Override
    public void run() {
        leggi();
    }
    
    /**
     * Legge il file senza tener conto del tipo di file
     * e lo mostra in output
     */
    public void leggi(){
        try (FileReader fr = new FileReader(nomeFile)) {
            int i;
            // leggo carattere per carattere e lo stampo
            while ((i = fr.read()) != -1)
                System.out.println((char) i);
            
            System.out.println("\n\r");
        } catch (IOException ex) {
            System.err.println("Errore in lettura!");
        }
    }
}