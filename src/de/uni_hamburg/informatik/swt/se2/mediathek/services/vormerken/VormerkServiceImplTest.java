package de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerken;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammServiceImpl;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandServiceImpl;

public class VormerkServiceImplTest {
	
	Medium _medium;
	Kunde _kunde1;
	Kunde _kunde2;
	Kunde _kunde3;
	Kunde _kunde4;
	VormerkServiceImpl _vormerkService;
	public VormerkServiceImplTest()
	{
		
		_kunde1 = new Kunde(new Kundennummer(123456), "ich", "wir");
		_kunde2 = new Kunde(new Kundennummer(234567), "er", "ihr");
		_kunde3 = new Kunde(new Kundennummer(345678), "sie", "sie");
		_kunde4 = new Kunde(new Kundennummer(456789), "es", "sie");
		_medium = new CD("CD2", "baz", "foo", 123);
		MedienbestandService medienbestand = new MedienbestandServiceImpl(new ArrayList<Medium>());
		 medienbestand.fuegeMediumEin(_medium);
		KundenstammService kundenstamm = new KundenstammServiceImpl(new ArrayList<Kunde>());
		kundenstamm.fuegeKundenEin(_kunde1);
		kundenstamm.fuegeKundenEin(_kunde2);
		kundenstamm.fuegeKundenEin(_kunde3);
		kundenstamm.fuegeKundenEin(_kunde4);
		_vormerkService = new VormerkServiceImpl(kundenstamm,medienbestand); 
	}
	@Test
	public void testeVormerken() 
	{
		assertTrue(_vormerkService.getVormerkungen().isEmpty());
		_vormerkService.merkeVor(_medium,_kunde1);
		assertTrue(_vormerkService.getVormerkkarte(_medium).istKundeBerechtigt(_kunde1));
		assertFalse(_vormerkService.getVormerkungen().isEmpty());
	}
	@Test
	public void testeEntferneVormerkung()
	{
		_vormerkService.entferneVormerkung(_medium,_kunde1);
		assertTrue(_vormerkService.getVormerkungen().isEmpty());
	}
	@Test
	public void testeIstVormerkenmoeglich()
	{
		assertTrue(_vormerkService.istVormerkenMoeglich(_medium));
		_vormerkService.merkeVor(_medium,_kunde1);
		_vormerkService.merkeVor(_medium,_kunde2);
		_vormerkService.merkeVor(_medium,_kunde3);
		assertFalse(_vormerkService.istVormerkenMoeglich(_medium));
	}
	@Test
	public void testegetVormerkkarte()
	{
		assertEquals(_vormerkService.getVormerkungen().get(_medium), _vormerkService.getVormerkkarte(_medium));
	}

}
