import java.util.*;

/**
 * Testklasse für die Buchkatalog-Implementierung
 * Demonstriert die Funktionalität der Buchkatalog-Klasse
 */
public class BuchkatalogTest {
    
    public static void main(String[] args) {
        // Mock StorageService erstellen
        StorageServiceMock storageService = new StorageServiceMock();
        
        // Buchkatalog mit dem Mock StorageService initialisieren
        Buchkatalog katalog = new Buchkatalog(storageService);
        
        // Test 1: Alle Bücher anzeigen
        System.out.println("Test 1: Alle Bücher anzeigen");
        List<Buch> alleBuecher = katalog.getAlleBuecher();
        for (int i = 0; i < alleBuecher.size(); i++) {
            System.out.println(i + ": " + alleBuecher.get(i));
        }
        System.out.println("Anzahl Bücher: " + katalog.getBuecherAnzahl());
        System.out.println();
        
        // Test 2: Buchsuche nach Titel
        System.out.println("Test 2: Buchsuche nach Titel 'Java'");
        List<Buch> suchergebnisse = katalog.buchsuche("Java");
        for (Buch buch : suchergebnisse) {
            System.out.println(buch);
        }
        System.out.println();
        
        // Test 3: Buchdetails anzeigen
        System.out.println("Test 3: Buchdetails für Buch mit ID 0");
        Buch buchDetails = katalog.buchDetails(0);
        if (buchDetails != null) {
            System.out.println("Titel: " + buchDetails.getTitel());
            System.out.println("ISBN: " + buchDetails.getIsbn());
            System.out.println("Autor: " + buchDetails.getAutor());
            System.out.println("Verlag: " + buchDetails.getVerlag());
            System.out.println("Erscheinungsjahr: " + buchDetails.getErscheinungsjahr());
            System.out.println("Beschreibung: " + buchDetails.getBeschreibung());
        } else {
            System.out.println("Buch nicht gefunden");
        }
        System.out.println();
        
        // Test 4: Rezensionen anzeigen
        System.out.println("Test 4: Rezensionen für Buch mit ID 0");
        List<Rezension> rezensionen = katalog.showRezensionen(0);
        if (rezensionen.isEmpty()) {
            System.out.println("Keine Rezensionen vorhanden");
        } else {
            for (Rezension rezension : rezensionen) {
                System.out.println(rezension);
            }
        }
        System.out.println();
        
        // Test 5: Rezension hinzufügen
        System.out.println("Test 5: Rezension hinzufügen für Buch mit ID 0");
        Rezension neueRezension = new Rezension(5, "Sehr gutes Buch!", new Date());
        boolean erfolg = katalog.reviewHinzufuegen(0, neueRezension);
        System.out.println("Rezension hinzugefügt: " + erfolg);
        
        // Rezensionen nach dem Hinzufügen anzeigen
        System.out.println("Rezensionen nach dem Hinzufügen:");
        rezensionen = katalog.showRezensionen(0);
        for (Rezension rezension : rezensionen) {
            System.out.println(rezension);
        }
        System.out.println();
        
        // Test 6: Neues Buch hinzufügen
        System.out.println("Test 6: Neues Buch hinzufügen");
        Autor autor = new Autor("Neuer Autor");
        Verlag verlag = new Verlag("Neuer Verlag");
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre("Sachbuch", "Informative Literatur"));
        
        Buch neuesBuch = new Buch(
            "Neues Testbuch", 
            "978-3-86680-192-9", 
            new Date(), 
            "Ein Testbuch für den Buchkatalog",
            autor,
            verlag,
            genres,
            new ArrayList<>()
        );
        
        erfolg = katalog.buchHinzufuegen(neuesBuch);
        System.out.println("Buch hinzugefügt: " + erfolg);
        
        // Alle Bücher nach dem Hinzufügen anzeigen
        System.out.println("Anzahl Bücher nach dem Hinzufügen: " + katalog.getBuecherAnzahl());
        alleBuecher = katalog.getAlleBuecher();
        for (int i = 0; i < alleBuecher.size(); i++) {
            System.out.println(i + ": " + alleBuecher.get(i));
        }
    }
}

/**
 * Mock-Implementierung des StorageService für Testzwecke
 */
class StorageServiceMock extends StorageService {
    
    @Override
    public List<Buch> ladeBuecher() {
        List<Buch> buecher = new ArrayList<>();
        
        // Beispieldaten erstellen
        Autor autor1 = new Autor("Max Mustermann");
        Autor autor2 = new Autor("Erika Musterfrau");
        
        Verlag verlag1 = new Verlag("Testverlag");
        Verlag verlag2 = new Verlag("Musterverlag");
        
        List<Genre> genres1 = new ArrayList<>();
        genres1.add(new Genre("Programmierung", "Bücher über Programmiersprachen"));
        
        List<Genre> genres2 = new ArrayList<>();
        genres2.add(new Genre("Roman", "Fiktionale Literatur"));
        
        List<Rezension> rezensionen1 = new ArrayList<>();
        rezensionen1.add(new Rezension(4, "Gutes Buch", new Date()));
        
        // Bücher erstellen
        Buch buch1 = new Buch(
            "Java Programmierung", 
            "978-3-86680-192-9", 
            new Date(), 
            "Ein Buch über Java-Programmierung",
            autor1,
            verlag1,
            genres1,
            rezensionen1
        );
        
        Buch buch2 = new Buch(
            "Der große Roman", 
            "978-3-12345-678-9", 
            new Date(), 
            "Ein fiktionaler Roman",
            autor2,
            verlag2,
            genres2,
            new ArrayList<>()
        );
        
        buecher.add(buch1);
        buecher.add(buch2);
        
        return buecher;
    }
    
    @Override
    public List<Rezension> ladeRezensionen() {
        List<Rezension> rezensionen = new ArrayList<>();
        rezensionen.add(new Rezension(4, "Gutes Buch", new Date()));
        return rezensionen;
    }
    
    @Override
    public void speichereRezension(Rezension rezension) {
        // In einem echten System würde hier die Rezension gespeichert werden
        System.out.println("Rezension gespeichert: " + rezension);
    }
}
