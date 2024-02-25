package gestionefile;

/**
 * @author Giacomo Contini
 */

import java.io.*;
import java.util.Scanner;

public class GestioneFile {
    static Lettore lettore = new Lettore("user.json");

    public static void serializeGestioneFile() {
        // Vengono create le istanze della classe "User.java"
        User[] users = {
                new User("username1", "password1"),
                new User("username2", "password2"),
                new User("username3", "password3")
        };

        // Gli oggetti vengono serializzati nel file "serial.ser"
        try (ObjectOutputStream object = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("serial.ser")))) {
            for (User user : users) {
                object.writeObject(user);
            }
        } catch (IOException e) {
            System.err.println("Errore nella scrittura del file 'serial.ser'");
        }
    }

    public static void streamGestioneFile() {
        String s = lettore.leggiStream("user.json");

        // I dati dell'utente vengono scritti in un file "user.csv"
        try (BufferedWriter scrittoreFile = new BufferedWriter(new FileWriter("user.csv"))) {
            scrittoreFile.write(s);
        } catch (IOException ex) {
            System.err.println("Errore nella scrittura del file 'user.csv'");
        }

        // I dati dell'utente vengono letti dal file "user.csv"
        try (BufferedReader lettoreFile = new BufferedReader(new FileReader("user.csv"))) {
            String l = lettoreFile.readLine();
            System.out.println(l);
        } catch (IOException ex) {
            System.err.println("Errore in lettura del file user.csv");
        }
    }

    public static void letturaGestioneFile() {
        // Lettura dei dati
        lettore.start();
    }

    public static void inserimentoDati() {
        Matrice m = new Matrice("GESTIONEFILE");
        Vigenere[] vigenereThreads = {
                new Vigenere(0, 12, 0, 12, m),
                new Vigenere(12, 26, 0, 12, m),
                new Vigenere(0, 12, 12, 26, m),
                new Vigenere(12, 26, 12, 26, m)
        };

        // Avvio e attesa del completamento dei thread
        for (Vigenere vigenere : vigenereThreads) {
            Thread t = new Thread(vigenere);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                System.err.println("Errore nel metodo join numero 1");
            }
        }

        // L'utente inserisce l'username e la password
        Scanner input = new Scanner(System.in);
        String username_cifrato = "";
        
        while(true) {
            System.out.println("Inserisci il tuo username: ");
            String username = input.nextLine();
          
            // Controllo della corretta scrittura dell'username da parte dell'utente
            if (username.matches("[A-Z]+") && !username.matches(".*\\d.*")) {
                System.out.println("Username valido. Cifratura in corso...");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.err.println("Errore nell'attesa");
                }
                username_cifrato = m.cifra(username);
                System.out.println("Cifratura effettuata con successo!");
                break;
            } else {
                System.out.println("Input non valido. L'username deve essere tutto in maiuscolo e senza numeri o caratteri speciali.");
            }
        }
        
        String password_cifrata = "";
        while(true) {
            System.out.println("Inserisci la tua password: ");
            String password = input.nextLine();
        
            // Controllo della corretta scrittura della password da parte dell'utente        
            if (password.matches("[A-Z]+") && !password.matches(".*\\d.*")) {
                System.out.println("Password valida. Cifratura in corso...");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.err.println("Errore nell'attesa");
                }
                password_cifrata = m.cifra(password);
                System.out.println("Password cifrata con successo!");
                break;
            } else {
            System.out.println("Input non valido. La password deve essere tutta in maiuscolo e senza numeri o caratteri speciali.");
            }
        }

        // I dati vengono scritti nel file "output.csv"
        Scrittore writer = new Scrittore("output.csv", username_cifrato, password_cifrata);
        Thread threadWriter = new Thread(writer);
        threadWriter.start();

        // I dati vengono copiati nel file di copia "copia.csv"
        Scrittore copiaFile = new Scrittore("copia.csv", username_cifrato, password_cifrata);
        Thread threadCopiaFile = new Thread(copiaFile);
        threadCopiaFile.start();
    }

    public static void main(String[] args) {
        letturaGestioneFile();
        inserimentoDati();
        streamGestioneFile();
        serializeGestioneFile();
    }
}