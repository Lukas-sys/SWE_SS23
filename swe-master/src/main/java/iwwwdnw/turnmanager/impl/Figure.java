package iwwwdnw.turnmanager.impl;

public class Figure {

	private String id;
	private Player owner;
	private Tile currentTile;

	public Figure(String id, Player owner) {
		this.id = id;
		this.owner = owner;
	}

	public Player getOwner() {
		return this.owner;
	}

	public void setTile(Tile tile) {
		this.currentTile = tile;
	}

	public Tile getCurrentTile() {
		return this.currentTile;
	}

	public String getFigureID() {
		return this.id;
	}

}
