package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.VormerkException;



public class Vormerkkarte
{
    private LinkedList<Kunde> _warteschlange;
    private Medium _medium;
    
    public Vormerkkarte(Medium medium)
    {
        _medium = medium;
        _warteschlange = new LinkedList<Kunde>();
    }
    
    public void vormerken(Kunde kunde) throws VormerkException
    {
        assert kunde != null : "Vorbedingung verletzt: kunde ist null";
        if(istVormerkenMoeglich(kunde))
        {
            _warteschlange.add(kunde);
        }
        else
        {
            throw new VormerkException();
        }
    }
    
    public void entferneErstenKunde()
    {
        _warteschlange.remove(0);
    }
    
    public void entferneKunde(int platz)
    {
        _warteschlange.remove(platz);
    }
    
    public Medium getMedium()
    {
        return _medium;
    }
    
    public boolean istLeer()
    {
        return _warteschlange.isEmpty();
    }
    
    public boolean istKundeBerechtigt(Kunde kunde)
    {
        return istLeer() || kunde.equals(_warteschlange.get(0));
    }
    
    public LinkedList<Kunde> getWarteschlange() 
    {
        return _warteschlange;
    }
    
    public int getSize()
    {
        return _warteschlange.size();
        
    }
    
    public boolean istVormerkenMoeglich(Kunde kunde)
    {
        return _warteschlange.size() < 3 || !(_warteschlange.contains(kunde));
    }

}
