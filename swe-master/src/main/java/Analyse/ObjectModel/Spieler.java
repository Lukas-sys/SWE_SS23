package Analyse.ObjectModel;
public class Spieler {



	/**
	 * @clientCardinality 2..6
	 * @clientNavigability NAVIGABLE
	 * @label spielt > 
	 * @link association
	 * @supplierCardinality 1
	 */
	Analyse.ObjectModel.Spiel lnkSpiel = null;
	/**
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @directed true
	 * @link association
	 * @supplierCardinality 5
	 */
	Analyse.ObjectModel.Figur lnkFigur = null;
}
