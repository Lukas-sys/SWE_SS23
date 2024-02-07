
package Analyse.ObjectModel;

public class Kategorie {


	

	

	
	/**
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @label < hat 
	 * @supplierCardinality *
	 */
	
	Analyse.ObjectModel.Duell lnkDuell = null;
	/**
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @label  besitzt >
	 * @supplierCardinality 1..*
	 */
	
	private Analyse.ObjectModel.Fragekarte lnkFragekarte;
	/**
	 * @clientCardinality 4..*
	 * @clientNavigability NAVIGABLE
	 * @label < speichert 
	 * @supplierCardinality 1
	 */
	
	Analyse.ObjectModel.Datenbank lnkDatenbank = null;
	/**
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @label v hat 
	 * @supplierCardinality 1
	 */

	Analyse.ObjectModel.Wissensstandsanzeiger lnkWissensstandsanzeiger = null;
}