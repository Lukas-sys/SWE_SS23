package iwwwdnw.spielzugmanager.port;

import Analyse.ObjectModel.Feld;

import java.util.List;

public interface Spieler {

	void setName(String name);
	void setColor(String color);
	
	String getName();
	String getColor();
	int getAge();
	
	List<? extends Feld> getStartFelder();
	List<? extends Feld> getHeimFelder();
	List<? extends Figur> getFiguren();
	

}
