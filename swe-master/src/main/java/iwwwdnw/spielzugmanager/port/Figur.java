package iwwwdnw.spielzugmanager.port;

import Analyse.ObjectModel.Feld;

public interface Figur {

	Spieler getBesitzer();
	void setAktuellesFeld(Feld feld);
	Feld getAktuellesFeld();
}
