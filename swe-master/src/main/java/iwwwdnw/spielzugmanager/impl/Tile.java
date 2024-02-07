package iwwwdnw.spielzugmanager.impl;

public class Tile {

	private int tileID;

	private Tile next = null;
	private Tile prev = null;
	private Tile connector = null;

	private String tileType;
	private boolean startTile;

	private Figure occupiedFigure = null;

	public Tile(int id, String tileType, boolean homeTile) {
		this.tileID = id;
		this.tileType = tileType;
		this.startTile = homeTile;
	}

	Tile(int id, String tileType, Figure figure) {
		this.tileID = id;
		this.tileType = tileType;
		this.occupiedFigure = figure;
	}

	public void setNextTile(Tile next) {
		this.next = next;
	}
	public void setPrevTile(Tile prev) {
		this.prev = prev;
	}
	public void setConnectorTile(Tile con) {
		this.connector = con;
	}

	public int getFieldID() {
		return this.tileID;
	}

	public String getTileType() {
		return this.tileType;
	}

	public boolean isStartTile() {
		return this.startTile;
	}

	public boolean isOccupied() {
		return this.occupiedFigure != null;
	}

	public Figure getFigure() {
		return this.occupiedFigure;
	}

	public void removeFigure() {
		this.occupiedFigure = null;
	}

	public void addFigure(Figure figure) {
		if (isOccupied()) {
			System.out.print("ups");
		}
		this.occupiedFigure = figure;
	}

	public Tile getNext() {
		return this.next;
	}

	public Tile getPrev() {
		return this.prev;
	}

	public Tile getConnector() {
		return this.connector;
	}



}
