package iwwwdnw.spielzugmanager.port;

import Analyse.ObjectModel.Feld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface SpielzugManager {

	Spieler getAktuellerSpieler();
	List<? extends Spieler> getAlleSpieler();
	
	HashMap<Integer, ArrayList<Feld>> getMoeglicheFelder();
	
	int getUebrigeBewegungen();
	
	Wuerfel getWurf();
	void werfeWuerfel();
	
	void bewegeFigur(Figur figur, Feld feld);
	void platziereFigur(Figur figur, Feld feld);

}
