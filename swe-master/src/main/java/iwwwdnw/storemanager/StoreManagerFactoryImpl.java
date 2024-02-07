package iwwwdnw.storemanager;

import iwwwdnw.statemachine.StateMachineFactory;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.statemachine.port.StateMachinePort;
import iwwwdnw.storemanager.impl.Color;
import iwwwdnw.storemanager.port.StoreManagement;
import iwwwdnw.storemanager.port.StoreManagerPort;
import iwwwdnw.storemanager.impl.StoreManagerImpl;
import iwwwdnw.turnmanager.impl.Board;
import iwwwdnw.turnmanager.impl.Figure;
import iwwwdnw.turnmanager.impl.Player;
import iwwwdnw.turnmanager.impl.Tile;

import java.util.ArrayList;

public class StoreManagerFactoryImpl implements StoreManagerFactory, StoreManagerPort, StoreManagement {

    private StateMachinePort stateMachinePort = StateMachineFactory.FACTORY.stateMachinePort();

    private StateMachine statemachine;

    private StoreManagerImpl storeManager;

    private void mkManager() {
        if (this.storeManager == null) {
            this.statemachine = this.stateMachinePort.stateMachine();
            this.storeManager = new StoreManagerImpl();
        }
    }

    @Override
    public StoreManagerPort storemanagerPort() {
        return this;
    }

    @Override
    public void storeNewPlayer(int id, String name, int age, Color color) {
        this.storeManager.storeNewPlayer(id, name, age, color);
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return this.storeManager.getPlayers();
    }

    @Override
    public void storeBoard(Board board) {
        this.storeManager.storeBoard(board);
    }

    @Override
    public Board getBoard() {
        return this.storeManager.getBoard();
    }

    @Override
    public Player getCurrentPlayer() {
        return this.storeManager.getCurrentPlayer();
    }

    @Override
    public int getOccupiedHomeTileCount() {
        return this.storeManager.getOccupiedHomeTileCount();
    }

    @Override
    public int getOccupiedStartTileCount() {
        return this.storeManager.getOccupiedStartTileCount();
    }

    @Override
    public void playerTried() {
        this.storeManager.playerTried();
    }

    @Override
    public int getRetries() {
        return this.storeManager.getRetries();
    }

    @Override
    public int getRollResult() {
        return this.storeManager.getRollResult();
    }

    @Override
    public void setRollResult(int result) {
        this.storeManager.setRollResult(result);
    }

    @Override
    public void nextPlayer() {
        this.storeManager.nextPlayer();
    }

    @Override
    public Figure getFigureByID(String id) {
        return this.storeManager.getFigureByID(id);
    }

    @Override
    public ArrayList<Tile> getVisited(Figure figure) {
        return this.storeManager.getVisited(figure);
    }

    @Override
    public void putVisited(Figure figure, ArrayList<Tile> visited) {
        this.storeManager.putVisited(figure, visited);
    }

    @Override
    public void addMove() {
        this.storeManager.addMove();
    }

    @Override
    public int getMoved() {
        return this.storeManager.getMoved();
    }

    @Override
    public StoreManagement storeManagement() {
        this.mkManager();
        return this;
    }
}
