package iwwwdnw.storemanager.port;

import iwwwdnw.storemanager.impl.Color;
import iwwwdnw.turnmanager.impl.Board;
import iwwwdnw.turnmanager.impl.Figure;
import iwwwdnw.turnmanager.impl.Player;
import iwwwdnw.turnmanager.impl.Tile;

import java.util.ArrayList;

public interface StoreManagement {

    void storeNewPlayer(int id, String name, int age, Color color);

    ArrayList<Player> getPlayers();

    void storeBoard(Board board);

    Board getBoard();

    Player getCurrentPlayer();

    int getOccupiedHomeTileCount();

    int getOccupiedStartTileCount();

    void playerTried();

    int getRetries();

    int getRollResult();

    void setRollResult(int result);

    void nextPlayer();

    Figure getFigureByID(String id);

    public ArrayList<Tile> getVisited(Figure figure);

    public void putVisited(Figure figure, ArrayList<Tile> visited);

    public void addMove();

    public int getMoved();

}
