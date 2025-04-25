import java.util.*;

public class Buchkatalog {
    private List<Buch> buecher;
    private StorageService storageService;

    public Buchkatalog(StorageService storageService) {
        this.storageService = storageService;
        this.buecher = storageService.ladeBuecher();
    }

    /**
     * Sucht nach Büchern mit dem angegebenen Titel
     * @param titel Der zu suchende Titel (Teilstring)
     * @return Liste der gefundenen Bücher
     */
    public List<Buch> buchsuche(String titel) {
        if (titel == null || titel.trim().isEmpty()) {
            return new ArrayList<>(buecher); // Alle Bücher zurückgeben, wenn kein Titel angegeben
        }
        
        List<Buch> ergebnisse = new ArrayList<>();
        String suchTitel = titel.toLowerCase().trim();
        
        for (Buch buch : buecher) {
            if (buch.getTitel().toLowerCase().contains(suchTitel)) {
                ergebnisse.add(buch);
            }
        }
        
        return ergebnisse;
    }

    /**
     * Gibt die Details eines Buches anhand seiner ID zurück
     * @param id Die ID des Buches
     * @return Das gefundene Buch oder null, wenn kein Buch mit dieser ID existiert
     */
    public Buch buchDetails(int id) {
        if (id < 0 || id >= buecher.size()) {
            return null; // Ungültige ID
        }
        
        return buecher.get(id);
    }

    /**
     * Fügt eine Rezension zu einem Buch hinzu
     * @param buchId Die ID des Buches
     * @param rezension Die hinzuzufügende Rezension
     * @return true, wenn die Rezension erfolgreich hinzugefügt wurde, sonst false
     */
    public boolean reviewHinzufuegen(int buchId, Rezension rezension) {
        if (buchId < 0 || buchId >= buecher.size() || rezension == null) {
            return false; // Ungültige ID oder Rezension
        }
        
        Buch buch = buecher.get(buchId);
        buch.getRezensionen().add(rezension);
        
        // Rezension im StorageService speichern
        storageService.speichereRezension(rezension);
        
        return true;
    }

    /**
     * Gibt alle Rezensionen eines Buches zurück
     * @param buchId Die ID des Buches
     * @return Liste der Rezensionen oder leere Liste, wenn keine Rezensionen vorhanden oder ID ungültig
     */
    public List<Rezension> showRezensionen(int buchId) {
        if (buchId < 0 || buchId >= buecher.size()) {
            return new ArrayList<>(); // Ungültige ID
        }
        
        Buch buch = buecher.get(buchId);
        return buch.getRezensionen();
    }
    
    /**
     * Fügt ein neues Buch zum Katalog hinzu
     * @param buch Das hinzuzufügende Buch
     * @return true, wenn das Buch erfolgreich hinzugefügt wurde, sonst false
     */
    public boolean buchHinzufuegen(Buch buch) {
        if (buch == null) {
            return false;
        }
        
        buecher.add(buch);
        return true;
    }
    
    /**
     * Gibt die Anzahl der Bücher im Katalog zurück
     * @return Anzahl der Bücher
     */
    public int getBuecherAnzahl() {
        return buecher.size();
    }
    
    /**
     * Gibt alle Bücher im Katalog zurück
     * @return Liste aller Bücher
     */
    public List<Buch> getAlleBuecher() {
        return new ArrayList<>(buecher);
    }
}
