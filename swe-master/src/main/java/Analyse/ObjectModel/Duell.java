package Analyse.ObjectModel;

public class Duell {



	

	
	/**
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @label besitzt >
	 * @supplierCardinality 1
	 */
	
	private Analyse.ObjectModel.Fragekarte lnkFragekarte;

	/**
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @label ^hat
	 * @supplierCardinality 1
	 */
	
	Analyse.ObjectModel.Herausfordernder lnkHerausfordernder = null;
	
	/**
	 * @clientCardinality 1
	 * @clientNavigability NAVIGABLE
	 * @label ^hat
	 * @supplierCardinality 1
	 */
	
	Analyse.ObjectModel.Herausforderer lnkHerausforderer = null;
}