package de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerken;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Vormerkkarte;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.VormerkException;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;

import java.util.HashMap;
import java.util.Map;

public class VormerkServiceImpl implements VormerkService
{
	private HashMap<Medium, Vormerkkarte> _vormerkungen;
    private KundenstammService _kundenstamm;
    private MedienbestandService _medienbestand;


	public VormerkServiceImpl(KundenstammService kundenstamm, MedienbestandService medienbestand)
    {
		_vormerkungen = new HashMap<Medium, Vormerkkarte>();
        _kundenstamm = kundenstamm;
        _medienbestand = medienbestand;
    }

    public void merkeVor(Medium medium, Kunde kunde)
    {
	    assert medium != null;
	    assert kunde != null;
        assert _medienbestand.enthaeltMedium(medium);
        assert _kundenstamm.enthaeltKunden(kunde);
        if(!_vormerkungen.containsKey(medium))
        {
            _vormerkungen.put(medium, new Vormerkkarte(medium));
        }
        Vormerkkarte karte = _vormerkungen.get(medium);
        try
        {
        	karte.vormerken(kunde);
        }
        catch (VormerkException e)
        {
        	
        }
       
    }


	public boolean istVormerkenMoeglich(Medium medium){
        assert medium != null;
        assert _medienbestand.enthaeltMedium(medium);
        if(!_vormerkungen.containsKey(medium))
        {
            return true;
        }
        return false;
    }


	public void entferneVormerkung(Medium medium, Kunde kunde)
    {
        assert medium != null;
        assert _kundenstamm.enthaeltKunden(kunde);
	    assert kunde != null;
	    if(_vormerkungen.get(medium)!=null)
	    {
	    Vormerkkarte vormerkkarte = _vormerkungen.get(medium);
	    vormerkkarte.entferneKunde(vormerkkarte.getWarteschlange().indexOf(kunde));
	    }
    }


	public Vormerkkarte getVormerkkarte(Medium medium)
    {
		if(_vormerkungen.containsKey(medium))
        {
            return _vormerkungen.get(medium);
        }
        return null;
    }
    
	public  HashMap<Medium, Vormerkkarte> getVormerkungen()
	{
		return _vormerkungen;
	}

}
