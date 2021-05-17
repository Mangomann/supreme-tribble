package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.VormerkException;

import org.junit.Test;

public class VormerkkarteTest
{
    private Vormerkkarte _karte;
    private Kunde _kunde1;
    private Kunde _kunde2;
    private Kunde _kunde3;
    private Kunde _kunde4;
    private Medium _medium;
    
    
    public VormerkkarteTest()
    {
        _kunde1 = new Kunde(new Kundennummer(123456), "ich", "du");
        _kunde2 = new Kunde(new Kundennummer(123456), "er", "sie");
        _kunde3 = new Kunde(new Kundennummer(123456), "es", "wir");
        _kunde4 = new Kunde(new Kundennummer(123456), "ihr", "sie");


        _medium = new CD("bar", "baz", "foo", 123);
        _karte = new Vormerkkarte(_medium);
    }
    @Test
    public void testeKonstruktor()
    {
        assertEquals(_medium, _karte.getMedium());
        assertTrue(_karte.istLeer());
    }
    @Test
    public void testeVormerken()
    {
        try
        {
            _karte.vormerken(_kunde1);
            _karte.vormerken(_kunde2);
            assertEquals(_kunde1, _karte.getWarteschlange().get(0));
            _karte.vormerken(_kunde1);
            assertFalse(2 == _karte.getSize());
        }
        catch (VormerkException e)
        {
            assertEquals(1, 2);
        }
        
    }
    
}
