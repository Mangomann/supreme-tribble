package de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerken;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.kundenstamm.KundenstammService;
import de.uni_hamburg.informatik.swt.se2.mediathek.services.medienbestand.MedienbestandService;
import java.util.Map;

public class VormerkServiceImpl implements VormerkService
{
	private Map<Medium, VormerkKarte> _vormerkungen;
    private KundenstammService _kundenstamm;
    private MedienbestandService _medienbestand;


	public VormerkServiceImpl(KundenstammService kundenstamm, MedienbestandService medienbestand)
    {
		_vormerkungen = new Map<Medium, VormerkKarte>();
        _kundenstamm = kundenstamm;
        _medienbestand = medienbestand;
    }

    public void merkeVor(Medium medium, Kunde kunde)
    {
	    assert medium != null;
	    assert kunde != null;
        assert _medienbestand.enthaeltMedium(medium);
        assert _kundenstamm.enthaeltKunden(kunde);
        if(_vormerkungen.containsKey(medium))
        {
            _vormerkungen.put(medium, new VormerkKarte(medium));
        }
        VormerkKarte karte = _vormerkungen.get(medium);
        karte.vormerken(kunde);
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
	    vormerkKarte = _vormerkungen.get(medium);
	    vormerkKarte.entferneKunden(vormerkKarte.getWarteschlange().indexOf(kunde));
    }


	public VormerkKarte getVormerkkarte(Medium medium)
    {
		if(_vormerkungen.containsKey(medium))
        {
            return _vormerkungen.get(medium);
        }
        return new VormerkKarte(medium);
    }
    
	public  Map<Medium, VormerkKarte> getVormerkungen()
	{
		return _vormerkungen;
	}

}
