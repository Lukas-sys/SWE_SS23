package iwwwdnw.spielzugmanager.impl;

import java.util.*;

public class Storage {

    private ArrayList<Player> players;
    private Queue<Player> playerQueue;
    private int retries = 3;
    private int moved = 0;
    private Map<Figure, ArrayList<Tile>> visitedTiles;


    public Storage(ArrayList<Player> players) {
        this.playerQueue = new LinkedList<>();
        this.players = players;
        this.playerQueue.addAll(this.players);
        this.visitedTiles = new HashMap<>();
    }

    public int getMoved() {
        return this.moved;
    }

    public void addMove() {
        this.moved++;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Player getCurrentPlayer() {
        return this.playerQueue.peek();
    }

    public int getRetries() {
        return this.retries;
    }

    public void playerTried() {
        this.retries--;
    }

    public void nextPlayer() {
        this.playerQueue.add(this.playerQueue.peek());
        this.playerQueue.remove();
        this.retries = 3;
        this.moved = 0;
        this.visitedTiles.clear();
    }

    public Player getPlayerByName(String name) throws Exception {
        for (Player player : players) {
            if (player.getName().equals(name)) return player;
        }
        throw new Exception(name+" does not exist.");
    }

    public ArrayList<Tile> getVisited(Figure figure) {
        return this.visitedTiles.get(figure);
    }

    public void putVisited(Figure figure, ArrayList<Tile> visited) {
        this.visitedTiles.put(figure, visited);
    }
}

