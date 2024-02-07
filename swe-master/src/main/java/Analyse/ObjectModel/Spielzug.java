package Analyse.ObjectModel;

public class Spielzug {




	
	/**
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @label < hat 
	 * @supplierCardinality 1
	 */
	
	Analyse.ObjectModel.Spiel lnkSpiel = null;
	/**
	 * @label benutzt v
	 * @link composition
	 */
	private Analyse.ObjectModel.Spieler lnkSpieler;
	/**
	 * @label hat >
	 * @link composition
	 */
	private Analyse.ObjectModel.Duell lnkDuell;
}