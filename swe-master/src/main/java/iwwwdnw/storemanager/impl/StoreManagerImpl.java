package iwwwdnw.storemanager.impl;

;

import iwwwdnw.storemanager.port.StoreManagement;
import iwwwdnw.turnmanager.impl.Board;
import iwwwdnw.turnmanager.impl.Figure;
import iwwwdnw.turnmanager.impl.Player;
import iwwwdnw.turnmanager.impl.Tile;

import java.util.*;

public class StoreManagerImpl implements StoreManagement {

    private ArrayList<Player> players = new ArrayList<>();
    private Queue<Player> playerQueue = new LinkedList<>();
    private int retries = 3;
    private int moved = 0;
    private Board board;
    private int rollResult = 0;
    private Map<Figure, ArrayList<Tile>> visitedTiles;

    @Override
    public void storeNewPlayer(int id, String name, int age, Color color) {
        Player player = new Player(id, name, age, color);
        players.add(player);
        playerQueue.add(player);
        this.visitedTiles = new HashMap<>();
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    @Override
    public void storeBoard(Board board) {
        this.board = board;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    public Player getCurrentPlayer() {
        return this.playerQueue.peek();
    }

    public int getOccupiedHomeTileCount() {
        int tileCount = 0;

        for (Tile tile : this.getCurrentPlayer().getHeimFelder()) {
            if (tile.isOccupied()) {
                tileCount++;
            }
        }

        return tileCount;
    }

    public int getOccupiedStartTileCount() {
        int tileCount = 0;

        for (Tile tile : this.getCurrentPlayer().getStartFelder()) {
            if (tile.isOccupied()) {
                tileCount++;
            }
        }

        return tileCount;
    }

    public void playerTried() {
        this.retries--;
    }

    public int getRetries() {
        return this.retries;
    }

    public int getRollResult() {
        return this.rollResult;
    }

    public void setRollResult(int result) {
        this.rollResult = result;
    }

    @Override
    public void nextPlayer() {
        this.playerQueue.add(this.playerQueue.peek());
        this.playerQueue.remove();
        this.retries = 3;
        this.moved = 0;
        this.visitedTiles.clear();
    }

    @Override
    public Figure getFigureByID(String id) {
        ArrayList<Player> players = this.getPlayers();
        ArrayList<Figure> figures = new ArrayList<>();

        for (Player player : players) {
            figures.addAll(player.getFiguren());
        }

        Figure figure = null;

        for (Figure fig : figures) {
            if (fig.getFigureID().equals(id)) {
                figure = fig;
                break;
            }
        }

        return figure;
    }

    @Override
    public ArrayList<Tile> getVisited(Figure figure) {
        return this.visitedTiles.get(figure);
    }

    @Override
    public void putVisited(Figure figure, ArrayList<Tile> visited) {
        this.visitedTiles.put(figure, visited);
    }

    @Override
    public void addMove() {
        this.moved++;
    }

    @Override
    public int getMoved() {
        return this.moved;
    }
}
