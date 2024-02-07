package iwwwdnw.turnmanager.port;

import iwwwdnw.storemanager.port.StoreManagement;
import iwwwdnw.turnmanager.impl.Board;
import iwwwdnw.turnmanager.impl.Player;

import java.util.ArrayList;

public interface TurnManagement {

    Board initializeBoard(ArrayList<Player> players);

    void showgetStartTilesForEachFigureInHome();

    int rollDice();

    boolean canMove();

    boolean canMoveFromHomeField();

    public void nextPlayer();

    String moveFigureFromHome(String input);

    void showPossibleMoveTiles();

    String moveFigure(String input);
}
