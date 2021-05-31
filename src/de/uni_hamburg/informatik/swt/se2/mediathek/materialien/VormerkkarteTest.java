package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

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
        
            _karte.vormerken(_kunde1);
            _karte.vormerken(_kunde2);
            assertEquals(_kunde1, _karte.getWarteschlange().get(0));
            
    }
    @Test
    public void testeEntferneErstenKunde()
    {
        _karte.vormerken(_kunde1);
        _karte.vormerken(_kunde2);
        _karte.entferneErstenKunde();
        assertEquals(_kunde2, _karte.getWarteschlange().get(0));
    }
    @Test
    public void testeEntferneKunde()
    {
        _karte.vormerken(_kunde1);
        _karte.vormerken(_kunde2);
        _karte.vormerken(_kunde3);
        _karte.entferneKunde(1);
        _karte.entferneErstenKunde();
        assertEquals(_kunde3, _karte.getWarteschlange().get(0));
    }
    @Test
    public void testeGetMedium()
    {
        assertEquals(_medium, _karte.getMedium());
    }
    @Test
    public void testeIstLeer()
    {
        assertTrue(_karte.istLeer());
        _karte.vormerken(_kunde1);
        assertFalse(_karte.istLeer());
    }
    @Test
    public void testeIstKundeBerechtigt()
    {
        assertTrue(_karte.istKundeBerechtigt(_kunde1));
        _karte.vormerken(_kunde2);
        assertFalse(_karte.istKundeBerechtigt(_kunde1));
        _karte.vormerken(_kunde1);
        assertFalse(_karte.istKundeBerechtigt(_kunde1));
        assertTrue(_karte.istKundeBerechtigt(_kunde2));
    }
    @Test
    public void testeGetWarteschlange()
    {
        _karte.vormerken(_kunde1);
        _karte.vormerken(_kunde2);
        _karte.vormerken(_kunde3);
        assertEquals(_kunde1, _karte.getWarteschlange().get(0));
    }
    @Test
    public void testeGetSize()
    {
        assertEquals(0, _karte.getSize());
        _karte.vormerken(_kunde1);
        assertEquals(1, _karte.getSize());
        _karte.vormerken(_kunde2);
        assertEquals(2, _karte.getSize());
        _karte.vormerken(_kunde3);
        assertEquals(3, _karte.getSize());
    }
    @Test
    public void testeIstVormerkenMoeglich()
    {
        assertTrue(_karte.istVormerkenMoeglich(_kunde1));
        _karte.vormerken(_kunde3);
        assertTrue(_karte.istVormerkenMoeglich(_kunde1));
        _karte.vormerken(_kunde1);
        assertFalse(_karte.istVormerkenMoeglich(_kunde1));
        _karte.entferneKunde(1);
        _karte.vormerken(_kunde2);
        _karte.vormerken(_kunde4);
        assertFalse(_karte.istVormerkenMoeglich(_kunde1));
    }
    @Test
    public void testeGetKunde()
    {
        assertNull(_karte.getKunde(1));
        _karte.vormerken(_kunde2);
        _karte.vormerken(_kunde4);
        assertEquals(_kunde4, _karte.getKunde(1));
        assertNull(_karte.getKunde(2));
        assertEquals(_kunde2, _karte.getKunde(0));
    }
}
