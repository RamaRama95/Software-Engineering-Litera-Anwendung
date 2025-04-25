import java.util.*;

/**
 * Controller-Klasse, die das ApplicationInterface implementiert
 * Dient als Vermittler zwischen der Benutzeroberfläche und dem Buchkatalog
 */
public class Controller implements ApplicationInterface {
    private Buchkatalog buchkatalog;
    private StorageService storageService;
    
    /**
     * Konstruktor für den Controller
     * @param storageService Der StorageService für den Datenzugriff
     */
    public Controller(StorageService storageService) {
        this.storageService = storageService;
        this.buchkatalog = new Buchkatalog(storageService);
    }
    
    /**
     * Sucht nach Büchern mit dem angegebenen Titel
     * @param titel Der zu suchende Titel
     * @return Liste der gefundenen Bücher
     */
    @Override
    public List<Buch> buchsuche(String titel) {
        return buchkatalog.buchsuche(titel);
    }
    
    /**
     * Gibt die Details eines Buches anhand seiner ID zurück
     * @param id Die ID des Buches
     * @return Das gefundene Buch oder null, wenn kein Buch mit dieser ID existiert
     */
    @Override
    public Buch buchdetails(int id) {
        return buchkatalog.buchDetails(id);
    }
    
    /**
     * Gibt alle Rezensionen eines Buches zurück
     * @param buchId Die ID des Buches
     * @return Liste der Rezensionen
     */
    @Override
    public List<Rezension> showRezensionen(int buchId) {
        return buchkatalog.showRezensionen(buchId);
    }
    
    /**
     * Fügt eine Rezension zu einem Buch hinzu
     * @param id Die ID des Buches
     * @param r Die hinzuzufügende Rezension
     */
    @Override
    public void reviewHinzufuegen(int id, Rezension r) {
        buchkatalog.reviewHinzufuegen(id, r);
    }
    
    /**
     * Gibt die Anzahl der Bücher im Katalog zurück
     * @return Anzahl der Bücher
     */
    public int getBuecherAnzahl() {
        return buchkatalog.getBuecherAnzahl();
    }
    
    /**
     * Gibt alle Bücher im Katalog zurück
     * @return Liste aller Bücher
     */
    public List<Buch> getAlleBuecher() {
        return buchkatalog.getAlleBuecher();
    }
}
