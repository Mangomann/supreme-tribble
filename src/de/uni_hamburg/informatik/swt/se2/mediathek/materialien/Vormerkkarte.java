package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import java.util.LinkedList;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
/**
 * Mit Hilfe von Vormerkkarten werden beim Vormerken eines Mediums alle relevanten
 * Daten notiert.
 * 
 * Sie beantwortet die folgenden Fragen: Welches Medium wurden vorgemerkt? Welche Personen 
 * haben in welcher Reihenfolge das Medium vorgemerkt?
 * 
 * Um die Verwaltung der Karten kuemmert sich der VerleihService
 * 
 * @version SoSe 2021
 */
public class Vormerkkarte
{
    //Eigenschaften einer Vormerkkarte
    /**
    * Warteschlange für wartende Kunden, die das Medium ausleihen wollen
    */
    private LinkedList<Kunde> _warteschlange;
    /**
    * Medium, das vorgemerkt werden soll
    */
    private Medium _medium;
    
    /**
     * Initialisert eine neue Vormerkkarte mit den gegebenen Daten.
     * 
     * @param medium Ein vorzumerkendes Medium.
     *
     * @require medium != null
     * 
     * @ensure #getMedium() == medium
     */
    public Vormerkkarte(Medium medium)
    {
        _medium = medium;
        _warteschlange = new LinkedList<Kunde>();
    }
    
    /**
    * Methode, die Kunde der Warteschlange hinzufügt
    * 
    * @param kunde Der Kunde für den vorgemerkt werden soll
    * 
    * @require kunde != null
    * @require istVormerkenMoeglich(kunde)
    * 
    * @ensure warteschlange.get(i) == kunde
    */
    public void vormerken(Kunde kunde)
    {
        assert kunde != null : "Vorbedingung verletzt: kunde ist null";
        assert istVormerkenMoeglich(kunde): "Vorbedingung verletzt: Vormerken ist nicht möglich";
        
        _warteschlange.add(kunde);
               
    }
    
    /**
    * Methode entfernt den ersten Vormerker aus der Warteschlange
    * die anderen rücken nach
    * 
    * @ensure _warteschlange.get(2) == null
    */
    public void entferneErstenKunde()
    {
        _warteschlange.remove(0);
    }
    
    /**
    * Methode entfernt einen Vormerker am angegebenen Platz aus der Warteschlange
    * 
    * @param platz Der Platz in der Warteschlange, aus der der Kunde entfernt werden soll
    *
    * @require platz >=0
    * @require platz <=2
    *
    * @ensure _warteschlange.size -1
    */
    public void entferneKunde(int platz)
    {
        assert platz>=0: "Vorbedingung verletzt: platz >=0";
        assert platz<=2: "Vorbedingung verletzt: platz <=2";
        _warteschlange.remove(platz);
    }
    
     /**
     * Gibt das Medium zurück.
     * 
     * @return Das Medium.
     * 
     * @ensure result != null
     */
    public Medium getMedium()
    {
        return _medium;
    }
    
    /**
    * gibt an, ob die Warteschlange leer ist
    * 
    * @return boolean, ob Warteschlange leer ist
    * 
    * @ensure result!= null
    */
    public boolean istLeer()
    {
        return _warteschlange.isEmpty();
    }
    
    /**
    * gibt an, ob Kunde berechtigt ist Medium auszuleihen
    *
    * @param kunde Der Kunde der geprüft werden soll
    *
    * @return boolean, ob Kunde berechtigt ist auszuleihen
    * 
    *
    * @ensure result != null
    */
    public boolean istKundeBerechtigt(Kunde kunde)
    {
        if(kunde == null)
        {
            return false;
        }
        return istLeer() || kunde.equals(getKunde(0));
    }
    
    /**
    * Gibt Warteschlange zurück
    *
    * @return Warteschlange
    * 
    * @ensure result != null
    */
    public LinkedList<Kunde> getWarteschlange() 
    {
        return _warteschlange;
    }
    
    /**
    * Gibt Größe der Warteschlange zurück
    * 
    * @return Länge der Warteschlange
    *
    * @ensure result != null
    */
    public int getSize()
    {
        return _warteschlange.size();
        
    }
    
    /**
    * Gibt an, ob für einen Kunden das vormerken möglich ist
    * @param kunde für den getestet werden soll, ob er vormerken kann
    *
    * @return boolean, ob vormerken für Kunden möglich ist.
    *
    * @require kunde != null
    * 
    * @ensure result != null
    */
    public boolean istVormerkenMoeglich(Kunde kunde)
    {
        assert kunde != null: "Vorbedingung verletzt: kunde != null";
        return _warteschlange.size() < 3 && !(_warteschlange.contains(kunde));
    }
    
    /**
    * Gibt Kunden in Warteschlange zurück
    *
    * @param i Stelle in Warteschlange
    * 
    * @return Kunde and Platz Warteschlange
    * 
    * @require i >=0
    * @require i <=2
    *
    * @ensure result != null
    */
    public Kunde getKunde(int i)
    {
        assert i>=0: "Vorbedingung verletzt: i >=0";
        assert i<=2: "Vorbedingung verletzt: i <=2";
        if(getSize() > i)
        {
            return _warteschlange.get(i);
        }
        return null;
    }

}
