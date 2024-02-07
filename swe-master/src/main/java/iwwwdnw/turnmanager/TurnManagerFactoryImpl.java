package iwwwdnw.turnmanager;

import iwwwdnw.statemachine.StateMachineFactory;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.statemachine.port.StateMachinePort;
import iwwwdnw.storemanager.StoreManagerFactory;
import iwwwdnw.storemanager.port.StoreManagement;
import iwwwdnw.turnmanager.impl.*;
import iwwwdnw.turnmanager.port.TurnManagement;
import iwwwdnw.turnmanager.port.TurnManagerPort;

import java.util.ArrayList;

public class TurnManagerFactoryImpl implements TurnManagerFactory, TurnManagerPort, TurnManagement {

    private StateMachinePort stateMachinePort = StateMachineFactory.FACTORY.stateMachinePort();
    private StateMachine stateMachine;
    private TurnManagerImpl turnManager;
    private StoreManagement storeManager;

    private void mkManager() {
        if (this.turnManager == null) {
            this.stateMachine = this.stateMachinePort.stateMachine();
            this.storeManager = StoreManagerFactory.FACTORY.storemanagerPort().storeManagement();
            this.turnManager = new TurnManagerImpl(storeManager);
        }
    }

    @Override
    public TurnManagerPort turnManagerPort() {
        return this;
    }

    @Override
    public TurnManagement turnManagement() {
        this.mkManager();
        return this;
    }

    @Override
    public Board initializeBoard(ArrayList<Player> players) {
        return this.turnManager.initializeBoard(players);
    }

    @Override
    public void showgetStartTilesForEachFigureInHome() {
        this.turnManager.showgetStartTilesForEachFigureInHome();
    }

    @Override
    public int rollDice() {
        return this.turnManager.rollDice();
    }

    @Override
    public boolean canMove() {
        return this.turnManager.canMove();
    }

    @Override
    public boolean canMoveFromHomeField() {
        return this.turnManager.canMoveFromHomeField();
    }

    @Override
    public void nextPlayer() {
        this.turnManager.nextPlayer();
    }

    @Override
    public String moveFigureFromHome(String input) {
        return this.turnManager.moveFigureFromHome(input);
    }

    @Override
    public void showPossibleMoveTiles() {
        this.turnManager.showPossibleMoveTiles();
    }

    @Override
    public String moveFigure(String input) {
        return this.turnManager.moveFigure(input);
    }


}
