package de.uni_hamburg.informatik.swt.se2.mediathek.services.vormerken;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.Kunde;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.VormerKarte;

public interface VormerkService
{
	/**
	 * Merkt ein Medium für den angegebenen Kunden vor, wenn das medium nicht
	 * schon von 3 anderen Kunden vorgemekt wurde.
	 *
	 * @require medium != null
	 * @require mediumImBestand(medium)
	 * @require kunde != null
	 * @require kundeImBestand(kunde)
	 * 
	 * @param medium Das Medium, welches Vorgemerkt werden soll
	 * @param kunde Der Kunde, welcher das Medium vormerken möchte
	**/
	public void merkeVor(Medium medium, Kunde kunde);

	/**
	 * Gibt zurück ob es möglich ist für einen weiteren Kunden das angegebene Medium
	 * vorzumerken.

	 * @require medium != null
	 * @require mediumImBestand(medium)
	 * 
	 * @param medium Das Medium auf welches überprüft werden soll
	 * @return Ist das Vormerken möglich?
	**/
	public boolean istVormerkenMoeglich(Medium medium);

	/**
	 * Hebt die Vormerkung für einen Kunden von einem Bestimmten Medium auf.
	 *
	 * @require medium != null
	 * @require mediumImBestand(medium)
	 * @require kunde != null
	 * @require kundeImBestand(kunde)
	 *
	 * @param medium 
	 * @param kunde
	**/
	public void entferneVormerkung(Medium medium, Kunde kunde);

	/**
	 * Gibt die Vormerkkarte zum angegebenen Medium zurück
	 *
	 * @require medium != null
	 * @require mediumImBestand(medium)
	 * @ensure result != null
	 *
	 * @param medium
	 * 
	**/
	public VormerkKarte getVormerkkarte(Medium medium);

	/**
	 * Gibt die Liste der Vormerkungen zurück
	 * @return Alle Vormerkkarten
	**/
	public Map<Medium, VormerkKarte> getVormerkungen();
}