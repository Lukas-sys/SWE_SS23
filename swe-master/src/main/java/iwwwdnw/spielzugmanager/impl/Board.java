package iwwwdnw.spielzugmanager.impl;

import java.util.ArrayList;

public class Board {
	final int MAX_PLAYERS = 6;
	final int TILES_BETWEEN_STARTTILES = 9;
	final int TILES_LESS_INNER_CIRCLE = 4;
	final int TILES_CONNECTS = 2;
	final int PER_PLAYER_LESS_TILES = 2;
	final int MODULO_MAX_OUT = 5;
	final int MODULO_MAX_IN = 3;
	private int players;

	ArrayList<Tile> tiles;

	public Board(int players) {
		int outTiles = players * (TILES_BETWEEN_STARTTILES + (PER_PLAYER_LESS_TILES * (MAX_PLAYERS-players))) + players;
		int innerTiles = players * (TILES_BETWEEN_STARTTILES + (PER_PLAYER_LESS_TILES * (MAX_PLAYERS-players)) - TILES_LESS_INNER_CIRCLE) + players;
		int connectTiles = 2 * players * TILES_CONNECTS;
		this.players=players;

		tiles = new ArrayList<>();
		this.createTiles(0, outTiles, "O");
		this.createTiles(outTiles, innerTiles, "I");
		this.createTiles(outTiles+innerTiles, connectTiles, "C");

		System.out.println();
		//this.showBoard(outTiles, innerTiles, connectTiles);
	}
	/**
	 * Create Tiles. Give each tile an ID, type (Out,In,Connector) and if it is a startingTile
	 * @param start
	 * @param amount
	 * @param type
	 */
	private void createTiles(int start, int amount, String type) {
		int counter = 0;
		int mOut = (TILES_BETWEEN_STARTTILES + (2 * (MAX_PLAYERS-players)))+1;
		int mIn = (TILES_BETWEEN_STARTTILES + (2 * (MAX_PLAYERS-players)))+1 - TILES_LESS_INNER_CIRCLE;

		for (int i = start; i < start+amount; i++) {
			if (type.equals("O")) {
				if (counter % mOut == 0) {
					tiles.add(new Tile(i, type, true));
				} else {
					tiles.add(new Tile(i, type, false));
				}
			} else if (type.equals("I")) {
				if (counter % mIn == 0) {
					tiles.add(new Tile(i, type, true));
				} else {
					tiles.add(new Tile(i, type, false));
				}
			} else {
				tiles.add(new Tile(i, type, false));
			}
			counter++;
		}
		this.connectTiles(start, start+amount);
	}

	private void connectTiles(int start, int stop) {
		if(tiles.get(start).getTileType().equals("C")) {
			//Verbinden der jeweiligen zwei C's 
			for (int i=start; i<stop; i+=2) {
				tiles.get(i).setNextTile(tiles.get(i+1));
				tiles.get(i+1).setPrevTile(tiles.get(i));
			}
			//Verbinden von den außen Ring die C-Tiles
			int i = start;
			int count = 0;
			for (int j=0; j < stop; j++) {
				if (tiles.get(j).getTileType().equals("C") || tiles.get(j).getTileType().equals("I")) {
					break;
				}
				if (j%(MODULO_MAX_OUT+(MAX_PLAYERS-players))==0) {
					if (count < 2*players) {
						tiles.get(i).setPrevTile(tiles.get(j));
						tiles.get(j).setConnectorTile(tiles.get(i));
						i+=2;
						count++;
					}
				}
			}
			//Verbinden von den innen Ring die C-Tiles
			int k = start+1;
			int counter = 0;
			for (int j=0; j<stop; j++) {
				if (tiles.get(j).getTileType().equals("C")) {
					break;
				} else if (tiles.get(j).getTileType().equals("O")) {
					//do nothing
				} else {
					if (counter % (MODULO_MAX_IN + (MAX_PLAYERS - players)) == 0) {
						tiles.get(k).setNextTile(tiles.get(j));
						tiles.get(j).setConnectorTile(tiles.get(k));
						k+=2;
					}
					counter++;
				}
			}
		} else {
			tiles.get(start).setNextTile(tiles.get(start+1));
			tiles.get(start).setPrevTile(tiles.get(stop-1));
			tiles.get(stop-1).setNextTile(tiles.get(start));
			tiles.get(stop-1).setPrevTile(tiles.get(stop-2));

			for (int i = start+1; i < stop-1; i++) {
				tiles.get(i).setNextTile(tiles.get(i+1));
				tiles.get(i).setPrevTile(tiles.get(i-1));
			}
		}

	}

	public ArrayList<Tile> createHomeTiles(Player player){
		ArrayList<Tile> homeTilesForPlayer = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			tiles.add(new Tile(tiles.size(), "H", player.getFiguren().get(i)));
			homeTilesForPlayer.add(tiles.get(tiles.size()-1));
			player.getFiguren().get(i).setTile(tiles.get(tiles.size()-1));
		}
		return homeTilesForPlayer;
	}

	private void showBoard(int o, int i, int c) {
		System.out.println("-------" + players +"--------");
		System.out.println("OUT: " + o);
		System.out.println("IN: " + i);
		System.out.println("CON: " + c);

		for (Tile tile:tiles) {
			if (tile.getConnector() == null) {
				System.out.println(tile.getFieldID() +"("+tile.getTileType()+")(" + tile.isStartTile() + ") next|prev " +tile.getNext().getFieldID()
						+ " | " + tile.getPrev().getFieldID() + " NO CONNECTION");
			} else {
				System.out.println(tile.getFieldID() +"("+tile.getTileType()+")(" + tile.isStartTile() + ") next|prev " +tile.getNext().getFieldID()
						+ " | " + tile.getPrev().getFieldID() + " CONNECTION: " + tile.getConnector().getFieldID()) ;
			}
		}
	}


	public static void main(String[] args) {
		Board b = new Board(6);
	}

}
